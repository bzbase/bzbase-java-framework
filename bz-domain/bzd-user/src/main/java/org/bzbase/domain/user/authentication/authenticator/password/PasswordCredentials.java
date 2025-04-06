package org.bzbase.domain.user.authentication.authenticator.password;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.bzbase.domain.user.valueobject.UserPoolId;
import org.bzbase.library.security.identity.Credentials;
import org.bzbase.library.security.identity.Identifier;
import org.bzbase.primitive.password.PlainPassword;

/**
 * 密码身份认证凭据
 *
 * @author legendjw
 */
@Getter
@AllArgsConstructor
public class PasswordCredentials implements Credentials {
    // 用户池ID
    private UserPoolId userPoolId;
    // 身份标识
    private Identifier identifier;
    // 密码
    private PlainPassword password;

    @Override
    public Identifier getIdentifier() {
        return identifier;
    }

    @Override
    public PlainPassword getCredentials() {
        return password;
    }
}
