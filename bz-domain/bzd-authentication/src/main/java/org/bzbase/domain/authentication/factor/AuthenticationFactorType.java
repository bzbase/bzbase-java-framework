package org.bzbase.domain.authentication.factor;

/**
 * 认证因素类型
 *
 * @author legendjw
 */
public enum AuthenticationFactorType {
    // 密码
    PASSWORD,
    // 密保问题
    SECURITY_ISSUE,
    // 手机号
    PHONE_NUMBER,
    // 邮箱地址
    EMAIL_ADDRESS,
    // 指纹
    FINGERPRINT,
    // 人脸
    FACE;
}
