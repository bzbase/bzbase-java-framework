package org.bzbase.primitive.password;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 哈希密码值对象测试
 *
 * @author legendjw
 */
class HashedPasswordTest {
    @Test
    void test_new_hashed_password() {
        HashedPassword hashedPassword = HashedPassword.of("MD5", "123456");

        assertThat(hashedPassword.getAlgorithm()).isEqualTo("MD5");
        assertThat(hashedPassword.getHash()).isEqualTo("123456");
        assertThat(hashedPassword.toString()).isEqualTo("MD5:123456");
        assertThat(HashedPassword.of("MD5:123456")).isEqualTo(hashedPassword);
    }
}
