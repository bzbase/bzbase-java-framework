package org.bzbase.primitive.password.hasher;

import com.password4j.BcryptFunction;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.types.Bcrypt;
import lombok.Value;
import org.bzbase.primitive.password.HashedPassword;
import org.bzbase.primitive.password.PlainPassword;

/**
 * 基于 BCrypt 的密码哈希器
 *
 * @author legendjw
 */
@Value
public class BCryptPasswordHasher implements PasswordHasher {
    public static final String ALGORITHM_NAME = "BCRYPT";

    /**
     * 定义 bcrypt 的次要版本: A, B, X, Y，默认为 B
     */
    String version;

    /**
     * 对数形式的轮数，默认为 10
     */
    int logRounds;

    /**
     * 密码哈希函数
     */
    BcryptFunction bcryptFunction;

    public BCryptPasswordHasher() {
        this("B", 10);
    }

    public BCryptPasswordHasher(String version, int logRounds) {
        this.version = version;
        this.logRounds = logRounds;
        this.bcryptFunction = BcryptFunction.getInstance(Bcrypt.valueOf(version), logRounds);
    }

    @Override
    public String getAlgorithmName() {
        return ALGORITHM_NAME;
    }

    @Override
    public HashedPassword hash(PlainPassword plainPassword) {
        Hash hash = Password.hash(plainPassword.getPlainText()).with(this.bcryptFunction);
        return HashedPassword.of(getAlgorithmName(), hash.getResult());
    }

    @Override
    public boolean verify(PlainPassword plainPassword, HashedPassword hashedPassword) {
        return this.bcryptFunction.check(plainPassword.getPlainText(), hashedPassword.getHash());
    }
}
