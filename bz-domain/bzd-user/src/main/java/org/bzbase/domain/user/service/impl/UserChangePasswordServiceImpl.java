package org.bzbase.domain.user.service.impl;

import org.bzbase.domain.user.User;
import org.bzbase.domain.user.infrastructure.UserRepository;
import org.bzbase.domain.user.service.UserChangePasswordService;
import org.bzbase.primitive.user.UserId;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.primitive.password.PlainPassword;
import org.bzbase.primitive.password.hasher.PasswordHasher;

import lombok.RequiredArgsConstructor;

/**
 * 用户密码修改领域服务实现
 *
 * @author legendjw
 */
@RequiredArgsConstructor
public class UserChangePasswordServiceImpl implements UserChangePasswordService {
    private final UserRepository userRepository;
	private final PasswordHasher passwordHasher;

    @Override
    public User changePassword(UserId userId, PlainPassword newPassword) {
        User user = userRepository.findById(userId).orElseThrow(() -> new DomainException("指定用户不存在"));
		user.changePassword(passwordHasher.hash(newPassword));
		return user;
    }

    @Override
    public User changePassword(UserId userId, PlainPassword oldPassword, PlainPassword newPassword) {
        User user = userRepository.findById(userId).orElseThrow(() -> new DomainException("指定用户不存在"));
		if (!user.verifyPassword(oldPassword, passwordHasher)) {
			throw new DomainException("旧密码错误");
		}
		user.changePassword(passwordHasher.hash(newPassword));
		return user;
    }
} 