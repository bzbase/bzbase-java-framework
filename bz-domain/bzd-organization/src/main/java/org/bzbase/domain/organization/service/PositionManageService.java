package org.bzbase.domain.organization.service;

import org.bzbase.domain.organization.Position;
import org.bzbase.domain.organization.valueobject.PositionId;

/**
 * 岗位管理服务
 */
public interface PositionManageService {
	/**
	 * 创建岗位
	 * 
	 * @param position 岗位
	 */
	Position createPosition(Position position);

	/**
	 * 修改岗位
	 * 
	 * @param position 岗位
	 */
	Position modifyPosition(Position position);

	/**
	 * 删除岗位
	 * 
	 * @param positionId 岗位ID
	 */
	Position deletePosition(PositionId positionId);
}