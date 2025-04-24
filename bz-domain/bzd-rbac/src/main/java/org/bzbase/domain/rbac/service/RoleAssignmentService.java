package org.bzbase.domain.rbac.service;

import java.util.Set;

import org.bzbase.domain.rbac.Role;
import org.bzbase.domain.rbac.RoleAssignment;
import org.bzbase.domain.rbac.valueobject.GrantedPermission;
import org.bzbase.domain.rbac.valueobject.RoleId;
import org.bzbase.domain.rbac.valueobject.Subject;
import org.bzbase.primitive.organization.OrganizationId;
import org.bzbase.primitive.tenant.TenantId;
import org.bzbase.primitive.user.UserId;

/**
 * 角色分配服务
 */
public interface RoleAssignmentService {
	/**
	 * 获取指定主体已分配的角色
	 *
	 * @param tenantId       租户ID
	 * @param organizationId 组织ID
	 * @param subject        主体
	 * @return 角色集合
	 */
	Set<Role> getAssignedRoles(TenantId tenantId, OrganizationId organizationId, Subject subject);

	/**
	 * 获取指定主体已授予的权限
	 *
	 * @param tenantId       租户ID
	 * @param organizationId 组织ID
	 * @param subject        主体
	 * @return 权限集合
	 */
	Set<GrantedPermission> getGrantedPermissions(TenantId tenantId, OrganizationId organizationId, Subject subject);

	/**
	 * 给指定主体分配角色
	 *
	 * @param tenantId       租户ID
	 * @param organizationId 组织ID
	 * @param subject        主体
	 * @param roleIds        角色ID
	 * @param operatedBy     操作人
	 * @return 角色分配
	 */
	RoleAssignment assignRoles(TenantId tenantId, OrganizationId organizationId, Subject subject, Set<RoleId> roleIds,
			UserId operatedBy);

	/**
	 * 撤销指定主体的所有角色分配
	 *
	 * @param tenantId       租户ID
	 * @param organizationId 组织ID
	 * @param subject        主体
	 * @param operatedBy     操作人
	 * @return 角色分配
	 */
	RoleAssignment revokeAssignment(TenantId tenantId, OrganizationId organizationId, Subject subject,
			UserId operatedBy);
}