package org.bzbase.domain.user.service.impl;

import java.time.Instant;
import java.util.Optional;

import org.bzbase.domain.user.User;
import org.bzbase.domain.user.infrastructure.UserRepository;
import org.bzbase.domain.user.service.UserManageService;
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
    public User createUser(User user) {
        // 验证用户身份
        for (Verifiable<Identifier> verifiable : user.getIdentifiers()) {
            Identifier identifier = verifiable.getValue();
            if (userRepository.findByUserPoolIdAndIdentifier(user.getPoolId(), identifier).isPresent()) {
                throw new DomainException(String.format("此%s已绑定其他用户", identifier.getTypeName()));
            }
        }

        return user;
    }

    @Override
    public User modifyUser(User user) {
        for (Verifiable<Identifier> verifiable : user.getIdentifiers()) {
            Identifier identifier = verifiable.getValue();
            Optional<User> existingUser = userRepository.findByUserPoolIdAndIdentifier(user.getPoolId(), identifier);
            if (existingUser.isPresent() && !existingUser.get().getId().equals(user.getId())) {
                throw new DomainException(String.format("此%s已绑定其他用户", identifier.getTypeName()));
            }
        }

        return user;
    }

    @Override
    public User deleteUser(UserId userId) {
        User user = getUserById(userId);
        return user;
    }

    private User getUserById(UserId userId) {
        return userRepository.findById(userId).orElseThrow(() -> new DomainException("用户不存在"));
    }
}