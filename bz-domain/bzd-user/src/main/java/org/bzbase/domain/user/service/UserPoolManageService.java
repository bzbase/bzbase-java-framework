package org.bzbase.domain.user.service;

import org.bzbase.domain.user.UserPool;
import org.bzbase.domain.user.valueobject.UserPoolId;
import org.bzbase.primitive.user.UserId;

/**
 * 用户池管理服务
 *
 * @author legendjw
 */
public interface UserPoolManageService {
	/**
	 * 创建用户池
	 *
	 * @param name          用户池名称
	 * @param code          用户池编码
	 * @param currentUserId 当前用户id
	 * @return 创建后的用户池
	 */
	UserPool createUserPool(String name, String code, UserId currentUserId);

	/**
	 * 修改用户池
	 *
	 * @param id            用户池id
	 * @param name          用户池名称
	 * @param code          用户池编码
	 * @param currentUserId 当前用户id
	 * @return 修改后的用户池
	 */
	UserPool modifyUserPool(UserPoolId id, String name, String code, UserId currentUserId);

	/**
	 * 删除用户池
	 *
	 * @param id            用户池id
	 * @param currentUserId 当前用户id
	 * @return 删除的用户池
	 */
	UserPool deleteUserPool(UserPoolId id, UserId currentUserId);
}
