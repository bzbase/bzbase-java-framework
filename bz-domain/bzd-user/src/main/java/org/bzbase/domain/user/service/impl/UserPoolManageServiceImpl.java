package org.bzbase.domain.user.service.impl;

import java.time.Instant;

import org.bzbase.domain.user.UserPool;
import org.bzbase.domain.user.infrastructure.UserPoolRepository;
import org.bzbase.domain.user.service.UserPoolManageService;
import org.bzbase.domain.user.valueobject.UserPoolId;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.IdGenerator;

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
	public UserPool createUserPool(UserPool userPool) {
		if (userPoolRepository.existsByCode(userPool.getCode())) {
			throw new DomainException("用户池编码已存在");
		}

		if (userPool.getId() == null) {
			userPool.setId(new UserPoolId(idGenerator.generate()));
		}
		userPool.setCreatedAt(Instant.now());

		return userPool;
	}

	@Override
	public UserPool modifyUserPool(UserPool userPool) {
		UserPool oldUserPool = getUserPoolById(userPool.getId());

		boolean isCodeChanged = !oldUserPool.getCode().equals(userPool.getCode());
		if (isCodeChanged && userPoolRepository.existsByCode(userPool.getCode())) {
			throw new DomainException("用户池编码已存在");
		}

		return userPool;
	}

	@Override
	public UserPool deleteUserPool(UserPoolId id) {
		UserPool userPool = getUserPoolById(id);
		return userPool;
	}

	private UserPool getUserPoolById(UserPoolId id) {
		return userPoolRepository.findById(id).orElseThrow(() -> new DomainException("指定用户池不存在"));
	}
}