package org.bzbase.domain.authentication.factor;

/**
 * 认证因素
 *
 * @author legendjw
 */
public interface AuthenticationFactor {
    /**
     * 获取因素分类
     *
     * @return 因素分类
     */
    AuthenticationFactorCategory getCategory();

    /**
     * 获取因素类型
     *
     * @return 因素类型
     */
    String getType();

    /**
     * 获取认证因素的值
     *
     * @return 认证因素本身的值
     */
    Object getValue();
}
