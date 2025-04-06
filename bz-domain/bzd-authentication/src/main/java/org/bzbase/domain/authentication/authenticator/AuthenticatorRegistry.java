package org.bzbase.domain.authentication.authenticator;

import java.util.ArrayList;
import java.util.List;

import org.bzbase.library.security.identity.Credentials;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 认证请求服务管理器
 *
 * @author legendjw
 */
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticatorRegistry {
    /**
     * 认证服务列表
     */
    private List<Authenticator> authenticators = new ArrayList<>();

    /**
     * 注册一个新的身份认证服务，通过此方法可以扩展新的身份认证服务，也可以覆盖已有的身份认证服务
     *
     * @param authenticator 身份认证服务
     */
    public void register(Authenticator authenticator) {
        if (!authenticators.contains(authenticator)) {
            authenticators.add(authenticator);
        }
    }

    /**
     * 获取认证服务
     *
     * @param credentials 认证凭据
     * @return 认证服务
     */
    public Authenticator getAuthenticator(Credentials credentials) {
        return authenticators.stream()
                .filter(authenticator -> authenticator.supports(credentials.getClass())).findFirst()
                .orElseThrow(() -> new AuthenticationException("不支持的认证凭据"));
    }
}
