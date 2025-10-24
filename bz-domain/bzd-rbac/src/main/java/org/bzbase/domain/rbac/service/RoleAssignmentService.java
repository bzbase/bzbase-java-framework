package org.bzbase.domain.rbac.service;

import org.bzbase.domain.rbac.Role;
import org.bzbase.domain.rbac.RoleAssignment;
import org.bzbase.domain.rbac.valueobject.GrantedPermission;
import org.bzbase.domain.rbac.valueobject.RoleId;
import org.bzbase.domain.rbac.valueobject.Subject;
import org.bzbase.primitive.user.UserId;

import java.util.Set;

/**
 * 角色分配服务
 */
public interface RoleAssignmentService {
	/**
	 * 获取指定主体已分配的角色
	 *
	 * @param subject        主体
	 * @return 角色集合
	 */
	Set<Role> getAssignedRoles(Subject subject);

	/**
	 * 获取指定主体已授予的权限
	 *
	 * @param subject        主体
	 * @return 权限集合
	 */
	Set<GrantedPermission> getGrantedPermissions(Subject subject);

	/**
	 * 给指定主体分配角色
	 *
	 * @param subject        主体
	 * @param roleIds        角色ID
	 * @param operatedBy     操作人
	 * @return 角色分配
	 */
	RoleAssignment assignRoles(Subject subject, Set<RoleId> roleIds, UserId operatedBy);

	/**
	 * 撤销指定主体的所有角色分配
	 *
	 * @param subject        主体
	 * @param operatedBy     操作人
	 * @return 角色分配
	 */
	RoleAssignment revokeAssignment(Subject subject, UserId operatedBy);
}