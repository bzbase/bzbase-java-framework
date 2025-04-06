package org.bzbase.domain.rbac.service;

import java.util.Set;

import org.bzbase.domain.rbac.Role;
import org.bzbase.domain.rbac.valueobject.RoleId;
import org.bzbase.primitive.user.UserId;

/**
 * 角色管理服务
 */
public interface RoleManageService {
	/**
	 * 创建角色
	 *
	 * @param parentId    父角色ID
	 * @param code        角色编码
	 * @param name        角色名称
	 * @param description 角色描述
	 * @param currentUserId 当前用户ID
	 * @return 角色
	 */
	Role createRole(RoleId parentId, String code, String name, String description, UserId currentUserId);

	/**
	 * 修改角色
	 *
	 * @param id          角色ID
	 * @param parentId    父角色ID
	 * @param code        角色编码
	 * @param name        角色名称
	 * @param description 角色描述
	 * @param currentUserId 当前用户ID
	 * @return 角色
	 */
	Role modifyRole(RoleId id, RoleId parentId, String code, String name, String description, UserId currentUserId);

	/**
	 * 删除角色
	 *
	 * @param id             角色ID
	 * @param currentUserId 当前用户ID
	 * @return 角色
	 */
	Role deleteRole(RoleId id, UserId currentUserId);

	/**
	 * 分配权限
	 * 
	 * @param id             角色ID
	 * @param permissions    权限
	 * @param currentUserId 当前用户ID
	 */
	Role assignPermissions(RoleId id, Set<String> permissions, UserId currentUserId);
}
