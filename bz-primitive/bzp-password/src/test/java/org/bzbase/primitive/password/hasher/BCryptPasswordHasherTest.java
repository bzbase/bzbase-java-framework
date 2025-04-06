package org.bzbase.primitive.password.hasher;

import org.bzbase.primitive.password.HashedPassword;
import org.bzbase.primitive.password.PlainPassword;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 基于 Bcrypt 的密码哈希器测试
 *
 * @author legendjw
 */
class BCryptPasswordHasherTest {
    @Test
    void test_bcrypt_password_hasher() {
        BCryptPasswordHasher passwordHasher = new BCryptPasswordHasher();
        HashedPassword hash = passwordHasher.hash(PlainPassword.of("123456"));

        assertThat(passwordHasher.verify(PlainPassword.of("wrong"), hash)).isFalse();
        assertThat(passwordHasher.verify(PlainPassword.of("123456"), hash)).isTrue();
    }
}
