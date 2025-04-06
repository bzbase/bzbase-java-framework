package org.bzbase.domain.user.infrastructure;

import org.bzbase.domain.user.User;
import org.bzbase.primitive.user.UserId;
import org.bzbase.domain.user.valueobject.UserPoolId;
import org.bzbase.library.ddd.type.Repository;
import org.bzbase.library.security.identity.Identifier;

import java.util.Optional;

/**
 * 用户资源库
 *
 * @author legendjw
 */
public interface UserRepository extends Repository<User, UserId> {
    /**
     * 根据用户池ID和身份标识获取用户
     *
     * @param userPoolId 用户池ID
     * @param identifier 身份标识
     * @return 用户
     */
    Optional<User> findByUserPoolIdAndIdentifier(UserPoolId userPoolId, Identifier identifier);

    /**
     * 根据用户池ID和外部身份标识获取用户
     *
     * @param userPoolId         用户池ID
     * @param externalIdentifier 外部身份标识
     * @return 用户
     */
    Optional<User> findByUserPoolIdAndExternalIdentifier(UserPoolId userPoolId, Identifier externalIdentifier);
}
