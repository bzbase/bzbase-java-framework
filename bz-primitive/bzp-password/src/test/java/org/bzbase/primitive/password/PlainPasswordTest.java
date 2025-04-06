package org.bzbase.primitive.password;

import org.bzbase.primitive.password.rule.CommonPasswordPolicy;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * 原始明文密码值对象测试
 *
 * @author legendjw
 */
class PlainPasswordTest {
    @Test
    void test_new_plain_password() {
        PlainPassword plainPassword = PlainPassword.of("123456");
        assertThat(plainPassword.getPlainText()).isEqualTo("123456");
        assertThat(plainPassword.toString()).isEqualTo("123456");
        assertThat(plainPassword.getStrength()).isEqualTo(PasswordStrength.WEAK);
    }

    @Test
    void test_new_plain_password_of_rule() {
        assertThatExceptionOfType(InvalidPasswordException.class).isThrownBy(() ->
                PlainPassword.of("12345678", CommonPasswordPolicy.AT_LEAST_ONE_LETTER_DIGIT)
        );
        assertThatNoException().isThrownBy(() ->
                PlainPassword.of("abcd5678", CommonPasswordPolicy.AT_LEAST_ONE_LETTER_DIGIT)
        );
    }
}
