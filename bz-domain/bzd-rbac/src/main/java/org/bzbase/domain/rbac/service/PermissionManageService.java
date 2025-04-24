package org.bzbase.domain.rbac.service;

import org.bzbase.domain.rbac.Permission;
import org.bzbase.domain.rbac.valueobject.PermissionId;

/**
 * 权限管理服务
 */
public interface PermissionManageService {
	/**
	 * 创建权限
	 *
	 * @param permission 权限
	 * @return 权限
	 */
	Permission createPermission(Permission permission);

	/**
	 * 修改权限
	 *
	 * @param permission 权限
	 * @return 权限
	 */
	Permission modifyPermission(Permission permission);

	/**
	 * 删除权限
	 *
	 * @param permissionId 权限ID
	 * @return 权限
	 */
	Permission deletePermission(PermissionId id);
}
