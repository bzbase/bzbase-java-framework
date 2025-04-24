package org.bzbase.domain.rbac.service.impl;

import java.time.Instant;
import java.util.Objects;
import java.util.Set;

import org.bzbase.domain.rbac.Role;
import org.bzbase.domain.rbac.infrastructure.RoleRepository;
import org.bzbase.domain.rbac.service.RoleManageService;
import org.bzbase.domain.rbac.specification.RoleCodeUniqueSpec;
import org.bzbase.domain.rbac.specification.RoleNameUniqueSpec;
import org.bzbase.domain.rbac.specification.RoleWithoutChildrenSpec;
import org.bzbase.domain.rbac.valueobject.GrantedPermission;
import org.bzbase.domain.rbac.valueobject.RoleId;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.IdGenerator;

import lombok.RequiredArgsConstructor;

/**
 * 角色管理服务实现
 */
@RequiredArgsConstructor
public class RoleManageServiceImpl implements RoleManageService {
	private final IdGenerator<String> idGenerator;
	private final RoleRepository roleRepository;

	@Override
	public Role createRole(Role role) {
		if (role.getId() == null) {
			role.setId(new RoleId(idGenerator.generate()));
		}
		role.setCreatedAt(Instant.now());

		if (!new RoleCodeUniqueSpec(roleRepository).isSatisfiedBy(role)) {
			throw new DomainException("角色编码已存在");
		}

		if (!new RoleNameUniqueSpec(roleRepository).isSatisfiedBy(role)) {
			throw new DomainException("角色名称已存在");
		}

		return role;
	}

	@Override
	public Role modifyRole(Role role) {
		Role oldRole = getRoleById(role.getId());

		boolean isCodeChanged = !Objects.equals(oldRole.getCode(), role.getCode());
		if (isCodeChanged && !new RoleCodeUniqueSpec(roleRepository).isSatisfiedBy(role)) {
			throw new DomainException("角色编码已存在");
		}

		boolean isNameChanged = !Objects.equals(oldRole.getName(), role.getName());
		if (isNameChanged && !new RoleNameUniqueSpec(roleRepository).isSatisfiedBy(role)) {
			throw new DomainException("角色名称已存在");
		}

		return role;
	}

	@Override
	public Role deleteRole(RoleId id) {
		Role role = getRoleById(id);
		
		if (!new RoleWithoutChildrenSpec(roleRepository).isSatisfiedBy(role)) {
			throw new DomainException("该角色下存在子角色，无法删除");
		}
		
		return role;
	}

	@Override
	public Role assignPermissions(RoleId id, Set<GrantedPermission> permissions) {
		Role role = getRoleById(id);
		role.assignPermissions(permissions);
		return role;
	}

	private Role getRoleById(RoleId id) {
		return roleRepository.findById(id).orElseThrow(() -> new DomainException("角色不存在"));
	}
}
