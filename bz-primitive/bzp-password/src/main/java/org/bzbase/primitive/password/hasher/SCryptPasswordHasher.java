package org.bzbase.primitive.password.hasher;

import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.ScryptFunction;
import lombok.Value;
import org.bzbase.primitive.password.HashedPassword;
import org.bzbase.primitive.password.PlainPassword;

/**
 * 基于 SCrypt 的密码哈希器
 *
 * @author legendjw
 */
@Value
public class SCryptPasswordHasher implements PasswordHasher {
    public static final String ALGORITHM_NAME = "SCRYPT";

    /**
     * 定义 CPU/内存成本，必须是 2 的幂，默认 65536
     */
    int workFactor;

    /**
     *定义内存块的大小，默认 8
     */
    int resources;

    /**
     * 定义攻击者的并行化成本，默认 1
     */
    int parallelization;

    /**
     * 定义最终派生密钥的期望长度，默认 64
     */
    int derivedKeyLength;

    /**
     * 盐值长度，默认 32
     */
    int saltLength;

    /**
     * 密码哈希函数
     */
    ScryptFunction scryptFunction;

    public SCryptPasswordHasher() {
        this(65536, 8, 1, 64, 32);
    }

    public SCryptPasswordHasher(int workFactor, int resources, int parallelization, int derivedKeyLength, int saltLength) {
        this.workFactor = workFactor;
        this.resources = resources;
        this.parallelization = parallelization;
        this.derivedKeyLength = derivedKeyLength;
        this.saltLength = saltLength;
        this.scryptFunction = ScryptFunction.getInstance(workFactor, resources, parallelization, derivedKeyLength);
    }

    @Override
    public String getAlgorithmName() {
        return ALGORITHM_NAME;
    }

    @Override
    public HashedPassword hash(PlainPassword plainPassword) {
        Hash hash = Password.hash(plainPassword.getPlainText()).addRandomSalt(this.saltLength).with(this.scryptFunction);
        return HashedPassword.of(getAlgorithmName(), hash.getResult());
    }

    @Override
    public boolean verify(PlainPassword plainPassword, HashedPassword hashedPassword) {
        return this.scryptFunction.check(plainPassword.getPlainText(), hashedPassword.getHash());
    }
}
