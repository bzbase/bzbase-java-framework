package org.bzbase.primitive.password.hasher;

import org.bzbase.primitive.password.HashedPassword;
import org.bzbase.primitive.password.PlainPassword;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 基于 Argon2 的密码哈希器测试
 *
 * @author legendjw
 */
class Argon2PasswordHasherTest {
    @Test
    void test_argon2_password_hasher() {
        Argon2PasswordHasher passwordHasher = new Argon2PasswordHasher();
        HashedPassword hash = passwordHasher.hash(PlainPassword.of("123456"));

        assertThat(passwordHasher.verify(PlainPassword.of("wrong"), hash)).isFalse();
        assertThat(passwordHasher.verify(PlainPassword.of("123456"), hash)).isTrue();
    }
}
