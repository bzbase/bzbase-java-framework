package org.bzbase.domain.rbac;

import org.bzbase.domain.rbac.valueobject.PermissionId;
import org.bzbase.library.ddd.type.LifecycleAggregateRoot;
import org.bzbase.primitive.user.UserId;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
public class Permission extends LifecycleAggregateRoot<PermissionId> {
	/**
	 * 权限ID
	 */
	private PermissionId id;

	/**
	 * 父权限ID
	 */
	private PermissionId parentId;

	/**
	 * 权限编码（唯一标识）
	 */
	private String code;

	/**
	 * 权限名称
	 */
	private String name;

	/**
	 * 创建权限
	 * 
	 * @param id            权限ID
	 * @param parentId      父权限ID
	 * @param code          权限编码
	 * @param name          权限名称
	 * @param currentUserId 当前用户ID
	 * @return 权限
	 */
	public static Permission create(PermissionId id, PermissionId parentId, String code, String name, UserId currentUserId) {
		Permission permission = Permission.builder()
				.id(id)
				.parentId(parentId)
				.code(code)
				.name(name)
				.build();
		permission.markAsCreated(currentUserId);
		return permission;
	}

	/**
	 * 修改权限
	 * 
	 * @param parentId      父权限ID
	 * @param code          权限编码
	 * @param name          权限名称
	 * @param currentUserId 当前用户ID
	 */
	public void modify(PermissionId parentId, String code, String name, UserId currentUserId) {
		this.parentId = parentId;
		this.code = code;
		this.name = name;
		this.markAsUpdated(currentUserId);
	}

	/**
	 * 删除权限
	 * 
	 * @param userId 当前用户ID
	 */
	public void delete(UserId userId) {
		this.markAsDeleted(userId);
	}
}
