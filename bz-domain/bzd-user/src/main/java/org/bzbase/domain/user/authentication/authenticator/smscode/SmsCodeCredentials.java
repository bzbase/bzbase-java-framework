package org.bzbase.domain.user.authentication.authenticator.smscode;

import org.bzbase.domain.user.authentication.identity.PhoneNumberIdentifier;
import org.bzbase.domain.user.valueobject.UserPoolId;
import org.bzbase.library.security.identity.Credentials;
import org.bzbase.library.security.identity.Identifier;
import org.bzbase.primitive.phonenumber.PhoneNumber;

import lombok.Value;

/**
 * 短信验证码身份凭证
 *
 * @author legendjw
 */
@Value
public class SmsCodeCredentials implements Credentials {
    // 用户池ID
    UserPoolId userPoolId;
    // 手机号
    PhoneNumber phoneNumber;
    // 短信验证码
    String code;
    // 使用场景
    String useCase;

    @Override
    public Identifier getIdentifier() {
        return new PhoneNumberIdentifier(phoneNumber);
    }

    @Override
    public Object getCredentials() {
        return code;
    }
}
