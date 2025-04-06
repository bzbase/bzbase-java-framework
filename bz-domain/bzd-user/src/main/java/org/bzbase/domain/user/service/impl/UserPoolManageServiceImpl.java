package org.bzbase.domain.user.service.impl;

import org.bzbase.domain.user.UserPool;
import org.bzbase.domain.user.infrastructure.UserPoolRepository;
import org.bzbase.domain.user.service.UserPoolManageService;
import org.bzbase.domain.user.valueobject.UserPoolId;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.IdGenerator;
import org.bzbase.primitive.user.UserId;

import lombok.RequiredArgsConstructor;

/**
 * 用户池管理服务实现
 *
 * @author legendjw
 */
@RequiredArgsConstructor
public class UserPoolManageServiceImpl implements UserPoolManageService {
	private final IdGenerator<String> idGenerator;
	private final UserPoolRepository userPoolRepository;

	@Override
	public UserPool createUserPool(String name, String code, UserId currentUserId) {
		if (userPoolRepository.existsByCode(code)) {
			throw new DomainException("用户池编码已存在");
		}
		return UserPool.create(new UserPoolId(idGenerator.generate()), name, code, currentUserId);
	}

	@Override
	public UserPool modifyUserPool(UserPoolId id, String name, String code, UserId currentUserId) {
		UserPool userPool = userPoolRepository.findById(id).orElseThrow(() -> new DomainException("指定用户池不存在"));
		boolean isCodeChanged = !userPool.getCode().equals(code);
		if (isCodeChanged && userPoolRepository.existsByCode(code)) {
			throw new DomainException("用户池编码已存在");
		}
		userPool.modify(name, code, currentUserId);
		return userPool;
	}

	@Override
	public UserPool deleteUserPool(UserPoolId id, UserId currentUserId) {
		UserPool userPool = userPoolRepository.findById(id).orElseThrow(() -> new DomainException("指定用户池不存在"));
		userPool.markAsDeleted(currentUserId);
		return userPool;
	}
}