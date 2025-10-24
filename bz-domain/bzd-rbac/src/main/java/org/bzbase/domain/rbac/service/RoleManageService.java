package org.bzbase.domain.rbac.service;

import org.bzbase.domain.rbac.Role;
import org.bzbase.domain.rbac.valueobject.GrantedPermission;
import org.bzbase.domain.rbac.valueobject.RoleId;

import java.util.Set;

/**
 * 角色管理服务
 */
public interface RoleManageService {
	/**
	 * 创建角色
	 *
	 * @param role 角色
	 * @return 角色
	 */
	Role createRole(Role role);

	/**
	 * 修改角色
	 *
	 * @param role 角色
	 * @return 角色
	 */
	Role modifyRole(Role role);

	/**
	 * 删除角色
	 *
	 * @param id 角色ID
	 * @return 角色
	 */
	Role deleteRole(RoleId id);

	/**
	 * 分配权限
	 * 
	 * @param id          角色ID
	 * @param permissions 授予的权限集合
	 * @return 角色
	 */
	Role assignPermissions(RoleId id, Set<GrantedPermission> permissions);
}
