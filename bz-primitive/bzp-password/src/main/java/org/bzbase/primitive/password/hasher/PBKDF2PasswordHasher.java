package org.bzbase.primitive.password.hasher;

import com.password4j.Hash;
import com.password4j.PBKDF2Function;
import com.password4j.Password;
import com.password4j.types.Hmac;
import lombok.Value;
import org.bzbase.primitive.password.HashedPassword;
import org.bzbase.primitive.password.PlainPassword;

import java.util.Base64;

/**
 * 基于 PBKDF2 的密码哈希器
 *
 * @author legendjw
 */
@Value
public class PBKDF2PasswordHasher implements PasswordHasher {
    public static final String ALGORITHM_NAME = "PBKDF2";

    /**
     * 定义 HMAC 家族的伪随机函数，包含：SHA1、SHA224、SHA256、SHA384、SHA512，默认 SHA256
     */
    String algorithm;

    /**
     * 定义伪随机函数与盐一起应用于密码的次数，默认 310000
     */
    int iterations;

    /**
     * 定义最终派生密钥的期望长度，默认 256
     */
    int keyLength;

    /**
     * 盐值长度，默认 32
     */
    int saltLength;

    /**
     * 密码哈希函数
     */
    PBKDF2Function pbkdf2Function;

    public PBKDF2PasswordHasher() {
        this("SHA256", 310000, 256, 32);
    }

    public PBKDF2PasswordHasher(String algorithm, int iterations, int keyLength, int saltLength) {
        this.algorithm = algorithm;
        this.iterations = iterations;
        this.keyLength = keyLength;
        this.saltLength = saltLength;
        this.pbkdf2Function = PBKDF2Function.getInstance(Hmac.valueOf(this.algorithm), this.iterations, this.keyLength);
    }

    @Override
    public String getAlgorithmName() {
        return ALGORITHM_NAME;
    }


    @Override
    public HashedPassword hash(PlainPassword plainPassword) {
        Hash hash = Password.hash(plainPassword.getPlainText()).addRandomSalt(this.saltLength).with(this.pbkdf2Function);

        // PBKDF2没有标准的字符串格式，这里采用：$algorithm$iterations$length$salt$hash
        String encodedHash = String.format("$%s$%s$%s$%s$%s",
                this.algorithm,
                this.iterations,
                this.keyLength,
                Base64.getEncoder().encodeToString(hash.getSaltBytes()),
                hash.getResult()
        );

        return HashedPassword.of(getAlgorithmName(), encodedHash);
    }

    @Override
    public boolean verify(PlainPassword plainPassword, HashedPassword hashedPassword) {
        String[] split = hashedPassword.getHash().split("\\$");
        if (split.length != 6) {
            throw new IllegalArgumentException("Invalid PBKDF2 hash format");
        }

        // 解析哈希值
        String algorithm = split[1];
        int iterations = Integer.parseInt(split[2]);
        int keyLength = Integer.parseInt(split[3]);
        String salt = split[4];
        String hash = split[5];

        PBKDF2Function pbkdf2Function = getPBKDF2Function(algorithm, iterations, keyLength);
        return pbkdf2Function.check(plainPassword.getPlainText().getBytes(), hash.getBytes(), Base64.getDecoder().decode(salt));
    }

    /**
     * 获取 PBKDF2 函数实例
     *
     * @param algorithm 算法名称
     * @param iterations 迭代次数
     * @param keyLength 密钥长度
     * @return PBKDF2 函数实例
     */
    private PBKDF2Function getPBKDF2Function(String algorithm, int iterations, int keyLength) {
        if (this.algorithm.equals(algorithm) && this.iterations == iterations && this.keyLength == keyLength) {
            return this.pbkdf2Function;
        }
        return PBKDF2Function.getInstance(Hmac.valueOf(algorithm), iterations, keyLength);
    }
}
