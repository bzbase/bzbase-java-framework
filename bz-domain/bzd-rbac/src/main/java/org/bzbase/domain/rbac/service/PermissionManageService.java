package org.bzbase.domain.rbac.service;

import org.bzbase.domain.rbac.Permission;
import org.bzbase.domain.rbac.valueobject.PermissionId;
import org.bzbase.primitive.user.UserId;

/**
 * 权限管理服务
 */
public interface PermissionManageService {
	/**
	 * 创建权限
	 *
	 * @param parentId      父权限ID
	 * @param code          权限编码
	 * @param name          权限名称
	 * @param currentUserId 当前用户ID
	 * @return 权限
	 */
	Permission createPermission(PermissionId parentId, String code, String name, UserId currentUserId);

	/**
	 * 修改权限
	 *
	 * @param id            权限ID
	 * @param parentId      父权限ID
	 * @param code          权限编码
	 * @param name          权限名称
	 * @param currentUserId 当前用户ID
	 * @return 权限
	 */
	Permission modifyPermission(PermissionId id, PermissionId parentId, String code, String name,
			UserId currentUserId);

	/**
	 * 删除权限
	 *
	 * @param id            权限ID
	 * @param currentUserId 当前用户ID
	 * @return 权限
	 */
	Permission deletePermission(PermissionId id, UserId currentUserId);
}
