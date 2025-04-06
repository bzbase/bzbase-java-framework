package org.bzbase.domain.user.infrastructure;

import org.bzbase.primitive.emailaddress.EmailAddress;

/**
 * 邮箱验证码验证服务
 *
 * @author legendjw
 */
public interface EmailCodeVerifyService {
    /**
     * 验证一个邮箱验证码
     *
     * @param email 邮箱
     * @param code 验证码
     * @param useCase 用例场景
     * @return 验证码是否正确
     */
    boolean verifyCode(EmailAddress email, String code, String useCase);
}
