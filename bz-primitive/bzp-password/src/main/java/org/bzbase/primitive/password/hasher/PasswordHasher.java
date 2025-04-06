package org.bzbase.primitive.password.hasher;

import org.bzbase.primitive.password.HashedPassword;
import org.bzbase.primitive.password.PlainPassword;

/**
 * 密码哈希器，负责将明文密码进行哈希处理
 *
 * @author legendjw
 */
public interface PasswordHasher {
    /**
     * 获取哈希算法的名称
     *
     * @return 哈希算法的名称
     */
    String getAlgorithmName();

    /**
     * 对明文密码进行哈希处理
     *
     * @param plainPassword 明文密码
     * @return 哈希密码
     */
    HashedPassword hash(PlainPassword plainPassword);

    /**
     * 验证明文密码是否与哈希密码匹配
     *
     * @param plainPassword 明文密码
     * @param hashedPassword 哈希密码
     * @return 是否匹配
     */
    boolean verify(PlainPassword plainPassword, HashedPassword hashedPassword);
}
