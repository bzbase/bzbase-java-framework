package org.bzbase.domain.user.service;

import org.bzbase.domain.user.UserPool;
import org.bzbase.domain.user.valueobject.UserPoolId;

/**
 * 用户池管理服务
 *
 * @author legendjw
 */
public interface UserPoolManageService {
	/**
	 * 创建用户池
	 *
	 * @param userPool 用户池
	 * @return 创建后的用户池
	 */
	UserPool createUserPool(UserPool userPool);

	/**
	 * 修改用户池
	 *
	 * @param userPool 用户池
	 * @return 修改后的用户池
	 */
	UserPool modifyUserPool(UserPool userPool);

	/**
	 * 删除用户池
	 *
	 * @param id 用户池id
	 * @return 删除的用户池
	 */
	UserPool deleteUserPool(UserPoolId id);
}
