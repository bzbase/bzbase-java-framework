package org.bzbase.domain.user.authentication.authenticator.password;

import java.util.HashSet;
import java.util.Set;

import org.bzbase.domain.authentication.authenticator.AuthenticationException;
import org.bzbase.domain.authentication.authenticator.Authenticator;
import org.bzbase.domain.authentication.factor.AuthenticationFactor;
import org.bzbase.domain.authentication.factor.AuthenticationFactorType;
import org.bzbase.domain.user.User;
import org.bzbase.domain.user.infrastructure.UserRepository;
import org.bzbase.library.security.claim.Claim;
import org.bzbase.library.security.claim.ClaimType;
import org.bzbase.library.security.identity.ClaimIdentity;
import org.bzbase.library.security.identity.Credentials;
import org.bzbase.library.security.identity.Identifier;
import org.bzbase.library.security.identity.Identity;
import org.bzbase.primitive.password.HashedPassword;
import org.bzbase.primitive.password.hasher.PasswordHasher;

import lombok.AllArgsConstructor;

/**
 * 账号密码认证器
 *
 * @author legendjw
 */
@AllArgsConstructor
public class PasswordAuthenticator implements Authenticator {
    private UserRepository userRepository;
    private PasswordHasher passwordHasher;

    @Override
    public String getAuthenticationType() {
        return "PASSWORD";
    }

    @Override
    public Identity authenticate(Credentials credentials) throws AuthenticationException {
        PasswordCredentials passwordCredentials = (PasswordCredentials) credentials;
        Identifier identifier = passwordCredentials.getIdentifier();
        // 获取用户
        User user = userRepository.findByUserPoolIdAndIdentifier(passwordCredentials.getUserPoolId(), identifier)
                .orElseThrow(() -> new AuthenticationException(String.format("此%s尚未注册", identifier.getTypeName())));
        // 获取用户的密码认证因素
        AuthenticationFactor passwordAuthFactor = user.getAuthFactorByType(AuthenticationFactorType.PASSWORD.name())
                .orElseThrow(() -> new AuthenticationException("用户名或密码错误"));
        // 验证明文密码是否正确
        if (!passwordHasher.verify(passwordCredentials.getCredentials(),
                HashedPassword.of(passwordAuthFactor.getValue().toString()))) {
            throw new AuthenticationException("用户名或密码错误");
        }
        // 构建认证身份
        Set<Claim> claims = new HashSet<>();
        claims.add(Claim.of(ClaimType.SUB, user.getId().toString()));
        return new ClaimIdentity(identifier, claims, getAuthenticationType());
    }

    @Override
    public boolean supports(Class<?> credentials) {
        return credentials.isAssignableFrom(PasswordCredentials.class);
    }
}
