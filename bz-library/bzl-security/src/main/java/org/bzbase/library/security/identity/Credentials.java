package org.bzbase.library.security.identity;

/**
 * 身份认证凭据
 *
 * @author legendjw
 */
public interface Credentials {
    /**
     * 获取认证身份标识，比如可以是一个用户名或者一个手机号。
     *
     * @return 认证身份标识
     */
    Identifier getIdentifier();

    /**
     * 获取认证凭据，用于证明认证身份是正确的，比如可以是一个密码，或者是一个短信验证码。
     *
     * @return 用于证明身份的认证凭据
     */
    Object getCredentials();
}
