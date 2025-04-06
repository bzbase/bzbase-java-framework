package org.bzbase.domain.user.authentication.authenticator.smscode;

import java.util.HashSet;
import java.util.Set;

import org.bzbase.domain.authentication.authenticator.AuthenticationException;
import org.bzbase.domain.authentication.authenticator.Authenticator;
import org.bzbase.domain.user.infrastructure.SMSCodeVerifyService;
import org.bzbase.library.security.claim.Claim;
import org.bzbase.library.security.claim.ClaimType;
import org.bzbase.library.security.identity.ClaimIdentity;
import org.bzbase.library.security.identity.Credentials;
import org.bzbase.library.security.identity.Identity;

import lombok.AllArgsConstructor;

/**
 * 短信验证码认证器
 *
 * @author legendjw
 */
@AllArgsConstructor
public class SmsCodeAuthenticator implements Authenticator {
    private SMSCodeVerifyService smsCodeVerifyService;

    @Override
    public String getAuthenticationType() {
        return "SMS_CODE";
    }

    @Override
    public Identity authenticate(Credentials credentials) throws AuthenticationException {
        // 转换为短信验证码凭据
        SmsCodeCredentials smsCodeCredentials = (SmsCodeCredentials) credentials;
        // 验证短信验证码是否正确
        if (!smsCodeVerifyService.verifyCode(smsCodeCredentials.getPhoneNumber(), smsCodeCredentials.getCode(),
                smsCodeCredentials.getUseCase())) {
            throw new AuthenticationException("短信验证码错误");
        }
        // 构建认证身份
        Set<Claim> claims = new HashSet<>();
        claims.add(Claim.of(ClaimType.PHONE_NUMBER, smsCodeCredentials.getPhoneNumber().toE164Format()));
        return new ClaimIdentity(smsCodeCredentials.getIdentifier(), claims, getAuthenticationType());
    }

    @Override
    public boolean supports(Class<?> credentials) {
        return credentials.isAssignableFrom(SmsCodeCredentials.class);
    }
}
