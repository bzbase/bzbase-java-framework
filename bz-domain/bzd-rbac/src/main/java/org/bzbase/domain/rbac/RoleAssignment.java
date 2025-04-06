package org.bzbase.domain.rbac;

import java.util.Set;

import org.bzbase.domain.rbac.valueobject.RoleAssignmentId;
import org.bzbase.domain.rbac.valueobject.RoleId;
import org.bzbase.domain.rbac.valueobject.Subject;
import org.bzbase.library.ddd.type.LifecycleAggregateRoot;
import org.bzbase.primitive.user.UserId;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * 角色分配
 * 
 * @author legendjw
 */
@Getter
@SuperBuilder(toBuilder = true)
public class RoleAssignment extends LifecycleAggregateRoot<RoleAssignmentId> {
	/**
	 * 角色分配ID
	 */
	private RoleAssignmentId id;

	/**
	 * 分配的主体
	 */
	private Subject subject;

	/**
	 * 分配的角色ID
	 */
	private Set<RoleId> roleIds;

	/**
	 * 创建角色分配
	 * 
	 * @param id            角色分配ID
	 * @param subject       分配的主体
	 * @param roleIds       分配的角色ID
	 * @param currentUserId 当前用户ID
	 * @return 角色分配
	 */
	public static RoleAssignment create(RoleAssignmentId id, Subject subject, Set<RoleId> roleIds,
			UserId currentUserId) {
		RoleAssignment roleAssignment = RoleAssignment.builder()
				.id(id)
				.subject(subject)
				.roleIds(roleIds)
				.build();
		roleAssignment.markAsCreated(currentUserId);
		return roleAssignment;
	}

	/**
	 * 分配角色
	 * 
	 * @param roleIds       角色ID
	 * @param currentUserId 当前用户ID
	 */
	public void assignRoles(Set<RoleId> roleIds, UserId currentUserId) {
		this.roleIds = roleIds;
		markAsUpdated(currentUserId);
	}

	/**
	 * 删除角色分配
	 * 
	 * @param currentUserId 当前用户ID
	 */
	public void delete(UserId currentUserId) {
		markAsDeleted(currentUserId);
	}
}
