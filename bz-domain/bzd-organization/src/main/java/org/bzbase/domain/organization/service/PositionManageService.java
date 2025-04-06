package org.bzbase.domain.organization.service;

import org.bzbase.domain.organization.Position;
import org.bzbase.domain.organization.valueobject.OrganizationId;
import org.bzbase.domain.organization.valueobject.PositionId;
import org.bzbase.primitive.user.UserId;

/**
 * 岗位管理服务
 */
public interface PositionManageService {
	/**
	 * 创建岗位
	 */
	Position createPosition(OrganizationId organizationId, String name, String code, String description,
			UserId currentUserId);

	/**
	 * 修改岗位
	 */
	Position modifyPosition(PositionId positionId, String name, String code, String description,
			UserId currentUserId);

	/**
	 * 删除岗位
	 */
	Position deletePosition(PositionId positionId, UserId currentUserId);
}