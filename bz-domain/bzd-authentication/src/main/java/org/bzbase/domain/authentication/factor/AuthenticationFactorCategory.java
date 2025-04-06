package org.bzbase.domain.authentication.factor;

/**
 * 认证因素分类
 *
 * @author legendjw
 */
public enum AuthenticationFactorCategory {
    // 知识因素：用户知道的东西，比如密码、安全问题
    KNOWLEDGE,
    // 拥有因素：用户拥有的东西，比如手机、身份证、USB 密钥
    POSSESSION,
    // 固有因素：用户自身的生命特征，比如指纹、人脸、虹膜
    INHERENCE,
    // 行为因素：用户的正常行为，比如通常访问的位置、IP 地址、可信设备，键盘敲击、打字速度、屏幕滑动等行为
    BEHAVIOR,
}
