package org.bzbase.domain.user.service;

import org.bzbase.primitive.user.UserId;
import org.bzbase.domain.user.User;
import org.bzbase.primitive.password.PlainPassword;

/**
 * 用户密码修改领域服务
 *
 * @author legendjw
 */
public interface UserChangePasswordService {
	/**
	 * 直接修改用户密码
	 *
	 * @param userId 用户id
	 * @param newPassword 新密码
	 * @return 用户
	 */
	User changePassword(UserId userId, PlainPassword newPassword);

	/**
	 * 通过验证旧密码修改用户密码
	 *
	 * @param userId 用户id
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @return 用户
	 */
	User changePassword(UserId userId, PlainPassword oldPassword, PlainPassword newPassword);
}
