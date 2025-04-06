package org.bzbase.domain.authentication.valueobject;

/**
 * 认证状态
 *
 * @author legendjw
 */
public enum AuthenticationStatus {
    /**
     * 已发起
     */
    INITIATED,
    /**
     * 认证成功
     */
    SUCCESSFUL,
    /**
     * 认证失败
     */
    FAILED,
}
