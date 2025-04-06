package org.bzbase.domain.user.authentication.factor;

import lombok.Value;

import org.bzbase.domain.authentication.factor.AuthenticationFactor;
import org.bzbase.domain.authentication.factor.AuthenticationFactorCategory;
import org.bzbase.domain.authentication.factor.AuthenticationFactorType;
import org.bzbase.primitive.password.HashedPassword;

/**
 * 密码认证因素
 *
 * @author legendjw
 */
@Value
public class PasswordFactor implements AuthenticationFactor {
    HashedPassword hashedPassword;

    @Override
    public AuthenticationFactorCategory getCategory() {
        return AuthenticationFactorCategory.KNOWLEDGE;
    }

    @Override
    public String getType() {
        return AuthenticationFactorType.PASSWORD.name();
    }

    @Override
    public Object getValue() {
        return hashedPassword;
    }
}
