package org.bzbase.domain.rbac.service;

import java.util.Set;

import org.bzbase.domain.rbac.RoleAssignment;
import org.bzbase.domain.rbac.valueobject.RoleId;
import org.bzbase.domain.rbac.valueobject.Subject;
import org.bzbase.primitive.user.UserId;

/**
 * 角色分配服务
 */
public interface RoleAssignService {
	/**
	 * 给指定主体分配角色
	 *
	 * @param subject       主体
	 * @param roleIds       角色ID
	 * @param currentUserId 当前用户ID
	 * @return 角色分配
	 */
	RoleAssignment assignRoles(Subject subject, Set<RoleId> roleIds, UserId currentUserId);

	/**
	 * 撤销主体的所有角色分配
	 *
	 * @param subject       主体
	 * @param currentUserId 当前用户ID
	 * @return 角色分配
	 */
	RoleAssignment revokeAssignment(Subject subject, UserId currentUserId);
}