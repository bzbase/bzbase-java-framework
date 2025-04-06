package org.bzbase.primitive.password.hasher;

import com.password4j.Argon2Function;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.types.Argon2;
import lombok.Value;
import org.bzbase.primitive.password.HashedPassword;
import org.bzbase.primitive.password.PlainPassword;

/**
 * 基于 Argon2 的密码哈希器
 *
 * @author legendjw
 */
@Value
public class Argon2PasswordHasher implements PasswordHasher {
    public static final String ALGORITHM_NAME = "ARGON2";

    /**
     * 定义要使用的内存量（以 kibibytes 为单位），默认 15
     */
    int memory;

    /**
     * 定义要执行的迭代次数，默认 2
     */
    int iterations;

    /**
     * 定义并行度（线程数），默认 1
     */
    int parallelism;

    /**
     * 定义最终派生密钥的所需长度，默认 32
     */
    int outputLength;

    /**
     * 定义算法的期望类型，包括 D(Argon2d)、I(Argon2i)、ID(Argon2id)，默认为 ID
     */
    String type;

    /**
     * 当前版本为 0x13（十进制 19）
     */
    int version;

    /**
     * 盐值长度，默认 32
     */
    int saltLength;

    /**
     * 密码哈希函数
     */
    Argon2Function argon2Function;

    public Argon2PasswordHasher() {
        this( 15, 2, 1, 32, "ID", 19, 32);
    }

    public Argon2PasswordHasher(int memory, int iterations, int parallelism, int outputLength, String type, int version, int saltLength) {
        this.memory = memory;
        this.iterations = iterations;
        this.parallelism = parallelism;
        this.outputLength = outputLength;
        this.version = version;
        this.type = "ID";
        this.saltLength = saltLength;
        this.argon2Function = Argon2Function.getInstance(this.memory, this.iterations, this.parallelism, this.outputLength, Argon2.valueOf(type), this.version);
    }

    @Override
    public String getAlgorithmName() {
        return ALGORITHM_NAME;
    }

    @Override
    public HashedPassword hash(PlainPassword plainPassword) {
        Hash hash = Password.hash(plainPassword.getPlainText()).addRandomSalt(this.saltLength).with(this.argon2Function);
        return HashedPassword.of(getAlgorithmName(), hash.getResult());
    }

    @Override
    public boolean verify(PlainPassword plainPassword, HashedPassword hashedPassword) {
        return this.argon2Function.check(plainPassword.getPlainText(), hashedPassword.getHash());
    }
}
