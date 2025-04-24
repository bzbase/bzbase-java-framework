package org.bzbase.domain.rbac.service.impl;

import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.bzbase.domain.rbac.Permission;
import org.bzbase.domain.rbac.Role;
import org.bzbase.domain.rbac.RoleAssignment;
import org.bzbase.domain.rbac.infrastructure.PermissionRepository;
import org.bzbase.domain.rbac.infrastructure.RoleAssignmentRepository;
import org.bzbase.domain.rbac.infrastructure.RoleRepository;
import org.bzbase.domain.rbac.service.RoleAssignmentService;
import org.bzbase.domain.rbac.valueobject.DataScope;
import org.bzbase.domain.rbac.valueobject.GrantedPermission;
import org.bzbase.domain.rbac.valueobject.RoleAssignmentId;
import org.bzbase.domain.rbac.valueobject.RoleId;
import org.bzbase.domain.rbac.valueobject.Subject;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.IdGenerator;
import org.bzbase.primitive.organization.OrganizationId;
import org.bzbase.primitive.tenant.TenantId;
import org.bzbase.primitive.user.UserId;

import lombok.RequiredArgsConstructor;

/**
 * 角色分配服务实现
 * 
 * @author legendjw
 */
@RequiredArgsConstructor
public class RoleAssignmentServiceImpl implements RoleAssignmentService {
	private final IdGenerator<String> idGenerator;
	private final RoleRepository roleRepository;
	private final PermissionRepository permissionRepository;
	private final RoleAssignmentRepository roleAssignmentRepository;

	@Override
	public Set<Role> getAssignedRoles(TenantId tenantId, OrganizationId organizationId, Subject subject) {
		RoleAssignment roleAssignment = roleAssignmentRepository.findBySubject(tenantId, organizationId, subject)
				.orElse(null);
		if (roleAssignment == null || roleAssignment.getRoleIds() == null || roleAssignment.getRoleIds().isEmpty()) {
			return Collections.emptySet();
		}

		return roleRepository.findByIds(tenantId, organizationId, roleAssignment.getRoleIds()).stream()
				.collect(Collectors.toSet());
	}

	@Override
	public Set<GrantedPermission> getGrantedPermissions(TenantId tenantId, OrganizationId organizationId,
			Subject subject) {
		Set<Role> roles = getAssignedRoles(tenantId, organizationId, subject);
		if (roles.isEmpty()) {
			return Collections.emptySet();
		}

		// 收集所有涉及的权限编码
		Set<String> permissionCodes = roles.stream()
				.flatMap(role -> role.getPermissions().stream())
				.map(GrantedPermission::getPermissionCode)
				.collect(Collectors.toSet());

		// 一次性获取所有相关权限节点
		Map<String, Permission> permissionMap = permissionRepository.findAll().stream()
				.filter(permission -> permissionCodes.contains(permission.getCode()))
				.collect(Collectors.toMap(Permission::getCode, permission -> permission));

		// 使用Map来存储每个权限编码对应的最高优先级的权限
		Map<String, GrantedPermission> grantedPermissionMap = new HashMap<>();
		Map<String, Integer> priorityMap = new HashMap<>();

		// 遍历所有角色的权限
		for (Role role : roles) {
			for (GrantedPermission grantedPermission : role.getPermissions()) {
				String permissionCode = grantedPermission.getPermissionCode();
				String dataScopeCode = grantedPermission.getDataScopeCode();
				Permission permission = permissionMap.get(permissionCode);
				if (permission == null) {
					continue;
				}

				// 是否包含重复的权限
				boolean isDuplicated = grantedPermissionMap.containsKey(permissionCode);
				if (!isDuplicated) {
					grantedPermissionMap.put(permissionCode, grantedPermission);
					if (permission.hasDataScope()) {
						// 记录当前权限的优先级
						Integer priority = permission.getDataScopes().stream()
								.filter(dataScope -> dataScope.getCode().equals(dataScopeCode))
								.map(DataScope::getPriority)
								.findFirst()
								.orElse(Integer.MAX_VALUE);
						priorityMap.put(permissionCode, priority);
					}
				}
				else if (permission.hasDataScope()) {
					// 如果权限有数据范围，则需要比较数据范围的优先级
					Integer currentPriority = permission.getDataScopes().stream()
							.filter(dataScope -> dataScope.getCode().equals(dataScopeCode))
							.map(DataScope::getPriority)
							.findFirst()
							.orElse(Integer.MAX_VALUE);
					
					// 获取已记录的优先级
					Integer existingPriority = priorityMap.getOrDefault(permissionCode, Integer.MAX_VALUE);
					
					// 如果当前优先级更高（数值更小），则更新权限和优先级
					if (currentPriority < existingPriority) {
						grantedPermissionMap.put(permissionCode, grantedPermission);
						priorityMap.put(permissionCode, currentPriority);
					}
				}
			}
		}

		return new HashSet<>(grantedPermissionMap.values());
	}

	@Override
	public RoleAssignment assignRoles(TenantId tenantId, OrganizationId organizationId, Subject subject,
			Set<RoleId> roleIds, UserId operatedBy) {
		// 验证角色是否存在
		List<Role> roles = roleRepository.findByIds(tenantId, organizationId, roleIds);
		if (roles.size() != roleIds.size()) {
			throw new DomainException("授权的角色错误");
		}

		// 查找现有的角色分配
		Optional<RoleAssignment> existingAssignment = roleAssignmentRepository.findBySubject(tenantId, organizationId,
				subject);

		// 如果存在，则更新角色
		if (existingAssignment.isPresent()) {
			RoleAssignment roleAssignment = existingAssignment.get();
			roleAssignment.assignRoles(roleIds, operatedBy);
			return roleAssignment;
		} else {
			// 如果不存在，则创建新的角色分配
			return RoleAssignment.builder()
					.id(new RoleAssignmentId(idGenerator.generate()))
					.tenantId(tenantId)
					.organizationId(organizationId)
					.subject(subject)
					.roleIds(roleIds)
					.operatedBy(operatedBy)
					.operatedAt(Instant.now())
					.build();
		}
	}

	@Override
	public RoleAssignment revokeAssignment(TenantId tenantId, OrganizationId organizationId, Subject subject,
			UserId operatedBy) {
		Optional<RoleAssignment> roleAssignmentOptional = roleAssignmentRepository.findBySubject(tenantId,
				organizationId, subject);
		if (!roleAssignmentOptional.isPresent()) {
			throw new DomainException("此主体下没有任何角色分配");
		}

		RoleAssignment roleAssignment = roleAssignmentOptional.get();
		roleAssignment.revokeAssignment(operatedBy);
		return roleAssignment;
	}
}