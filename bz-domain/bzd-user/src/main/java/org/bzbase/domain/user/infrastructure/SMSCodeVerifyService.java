package org.bzbase.domain.user.infrastructure;

import org.bzbase.primitive.phonenumber.PhoneNumber;

/**
 * 短信验证码验证服务
 *
 * @author legendjw
 */
public interface SMSCodeVerifyService {
    /**
     * 验证一个短信验证码
     *
     * @param phoneNumber 手机号
     * @param code 验证码
     * @param useCase 用例场景
     * @return 验证码是否正确
     */
    boolean verifyCode(PhoneNumber phoneNumber, String code, String useCase);
}
