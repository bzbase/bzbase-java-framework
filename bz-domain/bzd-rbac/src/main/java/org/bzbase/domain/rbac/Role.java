package org.bzbase.domain.rbac;

import java.util.Set;

import org.bzbase.domain.rbac.valueobject.RoleId;
import org.bzbase.library.ddd.type.LifecycleAggregateRoot;
import org.bzbase.primitive.user.UserId;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * 角色聚合根
 */
@Getter
@SuperBuilder(toBuilder = true)
public class Role extends LifecycleAggregateRoot<RoleId> {
	/**
	 * 角色ID
	 */
	private RoleId id;

	/**
	 * 父角色ID
	 */
	private RoleId parentId;

	/**
	 * 角色编码（唯一标识）
	 */
	private String code;

	/**
	 * 角色名称
	 */
	private String name;

	/**
	 * 角色描述
	 */
	private String description;

	/**
	 * 角色拥有的权限
	 */
	private Set<String> permissions;

	/**
	 * 创建角色
	 * 
	 * @param id            角色ID
	 * @param parentId      父角色ID
	 * @param code          角色编码
	 * @param name          角色名称
	 * @param description   角色描述
	 * @param currentUserId 当前用户ID
	 * @return 角色
	 */
	public static Role create(RoleId id, RoleId parentId, String code, String name, String description,
			UserId currentUserId) {
		Role role = Role.builder()
				.id(id)
				.parentId(parentId)
				.code(code)
				.name(name)
				.description(description)
				.build();
		role.markAsCreated(currentUserId);
		return role;
	}

	/**
	 * 修改角色
	 * 
	 * @param parentId      父角色ID
	 * @param name          角色名称
	 * @param code          角色编码
	 * @param description   角色描述
	 * @param currentUserId 当前用户ID
	 */
	public void modify(RoleId parentId, String name, String code, String description, UserId currentUserId) {
		this.parentId = parentId;
		this.name = name;
		this.code = code;
		this.description = description;
		this.markAsUpdated(currentUserId);
	}

	/**
	 * 删除角色
	 * 
	 * @param currentUserId 当前用户ID
	 */
	public void delete(UserId currentUserId) {
		this.markAsDeleted(currentUserId);
	}

	/**
	 * 分配权限
	 * 
	 * @param permissions   权限编码集合
	 * @param currentUserId 当前用户ID
	 */
	public void assignPermissions(Set<String> permissions, UserId currentUserId) {
		this.permissions = permissions;
		this.markAsUpdated(currentUserId);
	}

	/**
	 * 判断是否有指定权限
	 * 
	 * @param permission 权限编码
	 * @return 是否有权限
	 */
	public boolean hasPermission(String permission) {
		return permissions != null && permissions.contains(permission);
	}
}
