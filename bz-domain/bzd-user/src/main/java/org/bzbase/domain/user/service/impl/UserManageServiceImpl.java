package org.bzbase.domain.user.service.impl;

import java.util.Optional;
import java.util.Set;

import org.bzbase.domain.user.User;
import org.bzbase.domain.user.infrastructure.UserRepository;
import org.bzbase.domain.user.service.UserManageService;
import org.bzbase.domain.user.valueobject.UserPoolId;
import org.bzbase.domain.user.valueobject.UserProfile;
import org.bzbase.domain.user.valueobject.Verifiable;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.IdGenerator;
import org.bzbase.library.security.identity.Identifier;
import org.bzbase.primitive.user.UserId;

import lombok.RequiredArgsConstructor;

/**
 * 用户管理服务实现
 *
 * @author legendjw
 */
@RequiredArgsConstructor
public class UserManageServiceImpl implements UserManageService {
    private final IdGenerator<String> idGenerator;
    private final UserRepository userRepository;

    @Override
    public User createUser(UserPoolId userPoolId, UserProfile userProfile, Set<Verifiable<Identifier>> identifiers,
            UserId currentUserId) {
        // 生成用户ID
        UserId userId = new UserId(idGenerator.generate());
        // 创建用户
        User user = User.create(userId, userPoolId, userProfile, currentUserId);
        // 绑定用户本地身份
        for (Verifiable<Identifier> verifiable : identifiers) {
            Identifier identifier = verifiable.getValue();
            if (userRepository.findByUserPoolIdAndIdentifier(userPoolId, identifier).isPresent()) {
                throw new DomainException(String.format("此%s已绑定其他用户", identifier.getTypeName()));
            }
            user.bindIdentifier(verifiable);
        }

        return user;
    }

    @Override
    public User modifyUser(UserId userId, UserProfile userProfile, Set<Verifiable<Identifier>> identifiers,
            UserId currentUserId) {
        // 获取用户
        User user = userRepository.findById(userId).orElseThrow(() -> new DomainException("用户不存在"));
        // 修改用户信息
        user.modifyProfile(userProfile, currentUserId);
        // 绑定用户本地身份
        user.unbindAllIdentifiers();
        for (Verifiable<Identifier> verifiable : identifiers) {
            Identifier identifier = verifiable.getValue();
            Optional<User> existingUser = userRepository.findByUserPoolIdAndIdentifier(user.getPoolId(), identifier);
            if (existingUser.isPresent() && !existingUser.get().getId().equals(user.getId())) {
                throw new DomainException(String.format("此%s已绑定其他用户", identifier.getTypeName()));
            }
            user.bindIdentifier(verifiable);
        }

        return user;
    }

    @Override
    public User deleteUser(UserId userId, UserId currentUserId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new DomainException("用户不存在"));
        user.markAsDeleted(currentUserId);
        return user;
    }
}