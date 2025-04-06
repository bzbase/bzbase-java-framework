package org.bzbase.domain.authentication;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import org.bzbase.domain.authentication.valueobject.AuthenticationId;
import org.bzbase.domain.authentication.valueobject.AuthenticationStatus;
import org.bzbase.domain.authentication.valueobject.FailureMessage;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.AbstractAggregateRoot;
import org.bzbase.library.security.claim.Claim;
import org.bzbase.library.security.identity.ClaimIdentity;
import org.bzbase.library.security.identity.Identifier;
import org.bzbase.library.security.identity.Identity;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * 身份认证聚合根，表示一次身份认证的过程。
 *
 * @author legendjw
 */
@Getter
@SuperBuilder(toBuilder = true)
public class Authentication extends AbstractAggregateRoot<AuthenticationId> {
    /**
     * 认证ID
     */
    private AuthenticationId id;

    /**
     * 认证类型
     */
    private String authenticationType;

    /**
     * 身份标识
     */
    private Identifier identifier;

    /**
     * 认证主体声明
     */
    private Set<Claim> claims;

    /**
     * 认证状态
     */
    private AuthenticationStatus status;

    /**
     * 认证失败信息
     */
    private FailureMessage failureMessage;

    /**
     * 认证发起时间
     */
    private Instant initiatedAt;

    /**
     * 认证结束时间
     */
    private Instant finishedAt;

    /**
     * 发起一个认证
     *
     * @param id          认证ID
     * @param credentials 认证凭据
     * @return 认证实体
     */
    public static Authentication initiate(AuthenticationId id, String authenticationType, Identifier identifier) {
        return Authentication.builder()
                .id(id)
                .authenticationType(authenticationType)
                .identifier(identifier)
                .status(AuthenticationStatus.INITIATED)
                .initiatedAt(Instant.now().truncatedTo(ChronoUnit.SECONDS))
                .build();
    }

    /**
     * 是否是待认证的
     *
     * @return 待认证返回 true，否则返回 false
     */
    public boolean isInitiated() {
        return status.equals(AuthenticationStatus.INITIATED);
    }

    /**
     * 是否认证成功
     *
     * @return 认证成功返回 true，否则返回 false
     */
    public boolean isSuccessful() {
        return status.equals(AuthenticationStatus.SUCCESSFUL);
    }

    /**
     * 是否认证失败
     *
     * @return 认证失败返回 true，否则返回 false
     */
    public boolean isFailed() {
        return status.equals(AuthenticationStatus.FAILED);
    }

    /**
     * 获取认证身份
     *
     * @return 认证身份
     */
    public Identity getIdentity() {
        return isSuccessful() ? new ClaimIdentity(identifier, claims, authenticationType) : null;
    }

    /**
     * 认证成功
     */
    public void succeed(Identity identity) {
        if (!isInitiated()) {
            throw new DomainException("已认证无法重复认证");
        }
        this.status = AuthenticationStatus.SUCCESSFUL;
        this.finishedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        this.identifier = identity.getIdentifier();
        this.claims = identity.getClaims();
    }

    /**
     * 认证失败
     */
    public void fail(FailureMessage failureMessage) {
        if (!isInitiated()) {
            throw new DomainException("已认证无法重复认证");
        }
        this.status = AuthenticationStatus.FAILED;
        this.finishedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        this.failureMessage = failureMessage;
    }
}
