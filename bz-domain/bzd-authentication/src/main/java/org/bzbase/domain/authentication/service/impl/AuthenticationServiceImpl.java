package org.bzbase.domain.authentication.service.impl;

import org.bzbase.domain.authentication.Authentication;
import org.bzbase.domain.authentication.authenticator.AuthenticationException;
import org.bzbase.domain.authentication.authenticator.Authenticator;
import org.bzbase.domain.authentication.authenticator.AuthenticatorRegistry;
import org.bzbase.domain.authentication.service.AuthenticationService;
import org.bzbase.domain.authentication.valueobject.AuthenticationId;
import org.bzbase.domain.authentication.valueobject.FailureMessage;
import org.bzbase.library.ddd.type.IdGenerator;
import org.bzbase.library.security.identity.Credentials;
import org.bzbase.library.security.identity.Identity;

import lombok.RequiredArgsConstructor;

/**
 * 认证领域服务默认实现
 *
 * @author legendjw
 */
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final IdGenerator<String> idGenerator;
    private final AuthenticatorRegistry authenticatorRegistry;

    @Override
    public Authentication authenticate(Credentials credentials) {
        // 根据凭据类型获取认证器
        Authenticator authenticator = authenticatorRegistry.getAuthenticator(credentials);
        // 创建认证实体
        Authentication authentication = Authentication.initiate(
                new AuthenticationId(idGenerator.generate()),
                authenticator.getAuthenticationType(),
                credentials.getIdentifier());
        // 执行身份认证
        try {
            Identity identity = authenticator.authenticate(credentials);
            authentication.succeed(identity);
        } catch (AuthenticationException e) {
            authentication.fail(new FailureMessage(e.getClass().getSimpleName(), e.getMessage()));
        }

        return authentication;
    }
}
