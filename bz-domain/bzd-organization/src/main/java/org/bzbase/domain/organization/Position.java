package org.bzbase.domain.organization;

import org.bzbase.domain.organization.valueobject.OrganizationId;
import org.bzbase.domain.organization.valueobject.PositionId;
import org.bzbase.library.ddd.type.LifecycleAggregateRoot;
import org.bzbase.primitive.user.UserId;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * 岗位聚合根
 */
@Getter
@SuperBuilder(toBuilder = true)
public class Position extends LifecycleAggregateRoot<PositionId> {
	/**
	 * 岗位ID
	 */
	private PositionId id;

	/**
	 * 所属组织ID
	 */
	private OrganizationId organizationId;

	/**
	 * 岗位名称
	 */
	private String name;

	/**
	 * 岗位编码
	 */
	private String code;

	/**
	 * 岗位描述
	 */
	private String description;

	/**
	 * 创建岗位
	 */
	public static Position create(PositionId positionId, OrganizationId organizationId,
			String name, String code, String description, UserId currentUserId) {
		Position position = Position.builder()
				.id(positionId)
				.organizationId(organizationId)
				.name(name)
				.code(code)
				.description(description)
				.build();
		position.markAsCreated(currentUserId);
		return position;
	}

	/**
	 * 修改岗位信息
	 */
	public void modify(String name, String code, String description, UserId currentUserId) {
		this.name = name;
		this.code = code;
		this.description = description;
		this.markAsUpdated(currentUserId);
	}

	/**
	 * 删除岗位
	 */
	public void delete(UserId currentUserId) {
		this.markAsDeleted(currentUserId);
	}
}