package org.bzbase.domain.user.service.impl;

import org.bzbase.domain.user.User;
import org.bzbase.domain.user.UserPool;
import org.bzbase.domain.user.authentication.factor.PasswordFactor;
import org.bzbase.domain.user.authentication.identity.EmailAddressIdentifier;
import org.bzbase.domain.user.authentication.identity.PhoneNumberIdentifier;
import org.bzbase.domain.user.authentication.identity.UsernameIdentifier;
import org.bzbase.domain.user.infrastructure.UserPoolRepository;
import org.bzbase.domain.user.infrastructure.UserRepository;
import org.bzbase.domain.user.service.UserRegisterService;
import org.bzbase.domain.user.valueobject.UserPoolId;
import org.bzbase.domain.user.valueobject.UserProfile;
import org.bzbase.domain.user.valueobject.Verifiable;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.IdGenerator;
import org.bzbase.library.security.identity.Identifier;
import org.bzbase.primitive.emailaddress.EmailAddress;
import org.bzbase.primitive.password.HashedPassword;
import org.bzbase.primitive.password.PlainPassword;
import org.bzbase.primitive.password.hasher.PasswordHasher;
import org.bzbase.primitive.phonenumber.PhoneNumber;
import org.bzbase.primitive.user.UserId;
import org.bzbase.primitive.user.Username;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 用户注册领域服务默认实现
 *
 * @author legendjw
 */
@RequiredArgsConstructor
public class UserRegisterServiceImpl implements UserRegisterService {
    private final IdGenerator<String> idGenerator;
    private final PasswordHasher passwordHasher;
    private final UserPoolRepository userPoolRepository;
    private final UserRepository userRepository;

    @Override
    public User registerByUsername(@NonNull UserPoolId userPoolId, @NonNull Username username,
            @NonNull PlainPassword password, UserProfile userProfile) {
        UsernameIdentifier localIdentity = new UsernameIdentifier(username);
        Verifiable<Identifier> verifiableIdentity = Verifiable.verified(localIdentity);
        return registerByLocalIdentifier(userPoolId, verifiableIdentity, password, userProfile);
    }

    @Override
    public User registerByPhoneNumber(@NonNull UserPoolId userPoolId,
            @NonNull Verifiable<PhoneNumber> verifiablePhoneNumber, PlainPassword password, UserProfile userProfile) {
        PhoneNumberIdentifier localIdentity = new PhoneNumberIdentifier(verifiablePhoneNumber.getValue());
        Verifiable<Identifier> verifiableIdentity = Verifiable.from(localIdentity, verifiablePhoneNumber);
        return registerByLocalIdentifier(userPoolId, verifiableIdentity, password, userProfile);
    }

    @Override
    public User registerByEmailAddress(@NonNull UserPoolId userPoolId,
            @NonNull Verifiable<EmailAddress> verifiableEmailAddress, PlainPassword password, UserProfile userProfile) {
        EmailAddressIdentifier localIdentity = new EmailAddressIdentifier(verifiableEmailAddress.getValue());
        Verifiable<Identifier> verifiableIdentity = Verifiable.from(localIdentity, verifiableEmailAddress);
        return registerByLocalIdentifier(userPoolId, verifiableIdentity, password, userProfile);
    }

    @Override
    public User registerByLocalIdentifier(@NonNull UserPoolId userPoolId,
            @NonNull Verifiable<Identifier> verifiableIdentifier, PlainPassword password, UserProfile userProfile) {
        // 查找指定用户池
        UserPool userPool = userPoolRepository.findById(userPoolId).orElseThrow(() -> new DomainException("非法的用户池"));
        // 获取本地身份
        Identifier identifier = verifiableIdentifier.getValue();
        // 查找指定本地身份是否已被注册
        if (userRepository.findByUserPoolIdAndIdentifier(userPoolId, identifier).isPresent()) {
            throw new DomainException(String.format("此%s已被注册", identifier.getTypeName()));
        }
        // 创建一个新用户
        User user = User.create(new UserId(idGenerator.generate()), userPool.getId(), userProfile);
        // 绑定用户本地身份
        user.bindIdentifier(verifiableIdentifier);
        // 添加密码认证因素
        if (password != null) {
            HashedPassword hashedPassword = passwordHasher.hash(password);
            user.addAuthFactor(new PasswordFactor(hashedPassword));
        }
        return user;
    }

    @Override
    public User registerByExternalIdentifier(@NonNull UserPoolId userPoolId, @NonNull Identifier externalIdentifier,
            PlainPassword password, UserProfile userProfile) {
        UserPool userPool = userPoolRepository.findById(userPoolId).orElseThrow(() -> new DomainException("非法的用户池"));
        // 创建一个新用户
        User user = User.create(new UserId(idGenerator.generate()), userPool.getId(), userProfile);
        // 绑定用户外部身份
        user.bindExternalIdentifier(externalIdentifier);
        // 添加密码认证因素
        if (password != null) {
            HashedPassword hashedPassword = passwordHasher.hash(password);
            user.addAuthFactor(new PasswordFactor(hashedPassword));
        }
        return user;
    }
}
