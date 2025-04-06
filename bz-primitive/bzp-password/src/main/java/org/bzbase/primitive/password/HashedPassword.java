package org.bzbase.primitive.password;

import lombok.NonNull;
import lombok.Value;
import org.bzbase.library.ddd.type.ValueObject;

/**
 * 哈希密码值对象，代表明文密码经过哈希处理后的密码对象，包括指定的哈希算法名称和哈希值以及随机盐值。
 *
 * @author legendjw
 */
@Value
public class HashedPassword implements ValueObject {
    private static final String SEPARATOR = ":";

    /**
     * 哈希算法
     */
    String algorithm;

    /**
     * 哈希值
     */
    String hash;

    private HashedPassword(String algorithm, String hash) {
        this.algorithm = algorithm;
        this.hash = hash;
    }

    /**
     * 解析哈希密码字符串，将字符串解析为哈希密码对象
     *
     * @param hashPassword 哈希密码字符串，格式为：{@code algorithm:hash}，其中algorithm为哈希算法的名称，hash为哈希值
     * @return 返回一个哈希密码对象
     */
    public static HashedPassword of(@NonNull String hashPassword) {
        String[] split = hashPassword.split(SEPARATOR);
        if (split.length != 2) {
            throw new IllegalArgumentException("Invalid hash password format");
        }
        return new HashedPassword(split[0], split[1]);
    }

    /**
     * 创建一个哈希密码对象
     *
     * @param algorithm 加密算法的名称，用于标识使用的加密算法
     * @param hash 加密后的密码哈希值
     * @return 返回一个新的哈希密码对象
     */
    public static HashedPassword of(@NonNull String algorithm, @NonNull String hash) {
        return new HashedPassword(algorithm, hash);
    }

    @Override
    public String toString() {
        return String.format("%s%s%s", this.algorithm, SEPARATOR, this.hash);
    }
}
