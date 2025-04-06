package org.bzbase.domain.user;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.bzbase.domain.authentication.factor.AuthenticationFactor;
import org.bzbase.domain.authentication.factor.AuthenticationFactorType;
import org.bzbase.domain.user.authentication.factor.PasswordFactor;
import org.bzbase.domain.user.valueobject.UserPoolId;
import org.bzbase.domain.user.valueobject.UserProfile;
import org.bzbase.domain.user.valueobject.UserStatus;
import org.bzbase.domain.user.valueobject.Verifiable;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.LifecycleAggregateRoot;
import org.bzbase.library.security.identity.Identifier;
import org.bzbase.primitive.password.HashedPassword;
import org.bzbase.primitive.password.PlainPassword;
import org.bzbase.primitive.password.hasher.PasswordHasher;
import org.bzbase.primitive.user.UserId;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * 用户聚合根
 *
 * @author legendjw
 */
@Getter
@SuperBuilder(toBuilder = true)
public class User extends LifecycleAggregateRoot<UserId> {
    /**
     * 用户id
     */
    private UserId id;

    /**
     * 用户池id
     */
    private UserPoolId poolId;

    /**
     * 个人信息
     */
    private UserProfile profile;

    /**
     * 本地身份标识
     */
    @Builder.Default
    private Set<Verifiable<Identifier>> identifiers = new HashSet<>();

    /**
     * 外部身份标识
     */
    @Builder.Default
    private Set<Identifier> externalIdentifiers = new HashSet<>();

    /**
     * 认证因素
     */
    @Builder.Default
    private Set<AuthenticationFactor> authFactors = new HashSet<>();

    /**
     * 用户状态
     */
    private UserStatus status;

    /**
     * 状态最后修改时间
     */
    private Instant lastStatusChangedAt;

    /**
     * 状态变更原因
     */
    private String statusChangeReason;

    /**
     * 密码最后修改时间
     */
    private Instant lastPasswordChangedAt;

    /**
     * 最后登录时间
     */
    private Instant lastLoginAt;

    /**
     * 创建用户
     *
     * @param userId  用户id
     * @param poolId  用户池id
     * @param profile 用户信息
     * @return 用户
     */
    public static User create(UserId userId, UserPoolId poolId, UserProfile profile) {
        User user = User.builder()
                .id(userId)
                .poolId(poolId)
                .profile(profile)
                .status(UserStatus.ACTIVE)
                .build();
        user.markAsCreated();
        return user;
    }

    /**
     * 创建用户
     *
     * @param userId  用户id
     * @param poolId  用户池id
     * @param profile 用户信息
     * @param currentUserId 当前用户id
     * @return 用户
     */
    public static User create(UserId userId, UserPoolId poolId, UserProfile profile, UserId currentUserId) {
        User user = User.builder()
                .id(userId)
                .poolId(poolId)
                .profile(profile)
                .status(UserStatus.ACTIVE)
                .build();
        user.markAsCreated(currentUserId);
        return user;
    }

    /**
     * 绑定本地身份标识
     *
     * @param identifier 本地身份标识
     */
    public void bindIdentifier(Verifiable<Identifier> identifier) {
        if (this.identifiers.stream().map(Verifiable::getValue).anyMatch(identifier.getValue()::equals)) {
            throw new DomainException("此本地身份已绑定");
        }
        this.identifiers.add(identifier);
    }

    /**
     * 解绑本地身份标识
     *
     * @param identifierType 本地身份标识类型
     */
    public void unbindIdentifierByType(String identifierType) {
        this.identifiers.removeIf(verifiable -> verifiable.getValue().getType().equals(identifierType));
    }

    /**
     * 解绑所有本地身份标识
     */
    public void unbindAllIdentifiers() {
        this.identifiers.clear();
    }

    /**
     * 根据类型获取本地身份标识
     *
     * @param identifierType 本地身份标识类型
     * @return 本地身份标识
     */
    public Optional<Verifiable<Identifier>> getIdentifierByType(String identifierType) {
        return this.identifiers.stream()
                .filter(verifiable -> verifiable.getValue().getType().equals(identifierType))
                .findFirst();
    }

    /**
     * 绑定外部身份标识
     *
     * @param identifier 外部身份标识
     */
    public void bindExternalIdentifier(Identifier identifier) {
        if (this.externalIdentifiers.contains(identifier)) {
            throw new DomainException("此外部身份标识已绑定");
        }
        this.externalIdentifiers.add(identifier);
    }

    /**
     * 解绑外部身份标识
     *
     * @param identifierType 外部身份标识类型
     */
    public void unbindExternalIdentifierByType(String identifierType) {
        this.externalIdentifiers.removeIf(identifier -> identifier.getType().equals(identifierType));
    }

    /**
     * 添加认证因素
     *
     * @param authFactor 认证因素
     */
    public void addAuthFactor(AuthenticationFactor authFactor) {
        if (this.authFactors.contains(authFactor)) {
            throw new DomainException("此认证因素已存在");
        }
        this.authFactors.add(authFactor);
    }

    /**
     * 删除认证因素
     *
     * @param authFactor 认证因素
     */
    public void removeAuthFactorByType(String type) {
        this.authFactors.removeIf(authFactor -> authFactor.getType().equals(type));
    }

    /**
     * 获取认证因素
     *
     * @param type 认证因素类型
     * @return 认证因素
     */
    public Optional<AuthenticationFactor> getAuthFactorByType(String type) {
        return this.authFactors.stream().filter(authFactor -> authFactor.getType().equals(type)).findFirst();
    }

    /**
     * 修改个人信息
     *
     * @param userProfile 个人信息
     */
    public void modifyProfile(UserProfile userProfile) {
        this.profile = userProfile;
        this.markAsUpdated();
    }

    /**
     * 修改个人信息
     *
     * @param userProfile 个人信息
     * @param currentUserId 当前用户id
     */
    public void modifyProfile(UserProfile userProfile, UserId currentUserId) {
        this.profile = userProfile;
        this.markAsUpdated(currentUserId);
    }

    /**
     * 是否设置密码
     *
     * @return 是否设置密码
     */
    public boolean hasPassword() {
        return getPassword().isPresent();
    }

    /**
     * 获取密码
     *
     * @return 哈希密码
     */
    public Optional<HashedPassword> getPassword() {
        return getAuthFactorByType(AuthenticationFactorType.PASSWORD.name())
                .map(authFactor -> HashedPassword.of(authFactor.getValue().toString()));
    }

    /**
     * 验证密码
     *
     * @param plainPassword  明文密码
     * @param passwordHasher 密码哈希工具
     * @return 密码是否正确
     */
    public boolean verifyPassword(PlainPassword plainPassword, PasswordHasher passwordHasher) {
        return getPassword().map(hashedPassword -> passwordHasher.verify(plainPassword, hashedPassword)).orElse(false);
    }

    /**
     * 修改密码
     *
     * @param hashedPassword 新的哈希密码
     */
    public void changePassword(HashedPassword hashedPassword) {
        this.removeAuthFactorByType(AuthenticationFactorType.PASSWORD.name());
        this.addAuthFactor(new PasswordFactor(hashedPassword));
        this.lastPasswordChangedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
    }

    /**
     * 检查用户是否已注销
     *
     * @return 是否已注销
     */
    public boolean isDeactivated() {
        return this.status == UserStatus.DEACTIVATED;
    }

    /**
     * 注销用户
     * 
     * @param reason 注销原因
     * @throws DomainException 如果用户已经注销则抛出异常
     */
    public void deactivate(String reason) {
        if (isDeactivated()) {
            throw new DomainException("用户已注销");
        }
        this.status = UserStatus.DEACTIVATED;
        this.statusChangeReason = reason;
        this.lastStatusChangedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        this.markAsDeleted();
    }

    /**
     * 重新激活用户
     * 
     * @throws DomainException 如果用户未注销则抛出异常
     */
    public void reactivate() {
        if (!isDeactivated()) {
            throw new DomainException("用户未注销");
        }
        this.status = UserStatus.ACTIVE;
        this.statusChangeReason = null;
        this.lastStatusChangedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
    }

    /**
     * 检查用户是否已禁用
     *
     * @return 是否已禁用
     */
    public boolean isDisabled() {
        return this.status == UserStatus.DISABLED;
    }

    /**
     * 禁用用户
     * 
     * @param reason 禁用原因
     * @throws DomainException 如果用户已经禁用则抛出异常
     */
    public void disable(String reason) {
        if (isDisabled()) {
            throw new DomainException("用户已禁用");
        }
        if (isDeactivated()) {
            throw new DomainException("用户已注销");
        }
        this.status = UserStatus.DISABLED;
        this.statusChangeReason = reason;
        this.lastStatusChangedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
    }

    /**
     * 解除用户禁用状态
     * 
     * @throws DomainException 如果用户未禁用则抛出异常
     */
    public void enable() {
        if (!isDisabled()) {
            throw new DomainException("用户未禁用");
        }
        this.status = UserStatus.ACTIVE;
        this.statusChangeReason = null;
        this.lastStatusChangedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
    }

    /**
     * 检查用户是否可登录
     *
     * @return 是否可登录
     */
    public boolean canLogin() {
        return this.status == UserStatus.ACTIVE || this.status == UserStatus.INACTIVE;
    }

    /**
     * 用户登录
     */
    public void login() {
        if (!canLogin()) {
            throw new DomainException("用户状态异常不可登录");
        }
        this.lastLoginAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
    }
}
