package org.bzbase.primitive.password.hasher;

import org.bzbase.primitive.password.HashedPassword;
import org.bzbase.primitive.password.PlainPassword;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 基于 Scrypt 的密码哈希器测试
 *
 * @author legendjw
 */
class SCryptPasswordHasherTest {
    @Test
    void test_scrypt_password_hasher() {
        SCryptPasswordHasher passwordHasher = new SCryptPasswordHasher();
        HashedPassword hash = passwordHasher.hash(PlainPassword.of("123456"));

        assertThat(passwordHasher.verify(PlainPassword.of("wrong"), hash)).isFalse();
        assertThat(passwordHasher.verify(PlainPassword.of("123456"), hash)).isTrue();
    }
}
