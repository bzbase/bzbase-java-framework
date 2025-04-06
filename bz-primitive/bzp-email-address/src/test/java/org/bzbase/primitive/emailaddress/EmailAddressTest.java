package org.bzbase.primitive.emailaddress;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 邮箱地址测试
 *
 * @author legendjw
 */
class EmailAddressTest {
    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "  ",
            "abcde",
            "@gmail.com",
            "abc.example.com",
            "abc@abc@example.com",
            "John Smith <john@smith.com>"
    })
    void test_invalid_email(String email) {
        assertThatExceptionOfType(InvalidEmailAddressException.class).isThrownBy(() -> EmailAddress.of(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            " abc@gmail.com",
            "abc123@gmail.com ",
            "123@gmail.com",
            "a_b-c@gmail.com",
            "a.b@gmail.com",
            "你好@gmail.com",
    })
    void test_valid_email(String email) {
        assertThatNoException().isThrownBy(() -> EmailAddress.of(email));
    }

    @Test
    void test_new_email() {
        EmailAddress emailAddress = EmailAddress.of(" abc@gmail.com ");
        assertThat(emailAddress).satisfies(e -> {
            assertThat(e.getAddress()).isEqualTo("abc@gmail.com");
            assertThat(e.toString()).isEqualTo("abc@gmail.com");
            assertThat(e.getLocalPart()).isEqualTo("abc");
            assertThat(e.getDomain()).isEqualTo("gmail.com");
        });
    }

    @Test
    void test_email_equals() {
        EmailAddress sameEmailAddress1 = EmailAddress.of("abc@gmail.com");
        EmailAddress sameEmailAddress2 = EmailAddress.of("abc@gmail.com");
        EmailAddress differentEmailAddress = EmailAddress.of("different@gmail.com");

        assertThat(sameEmailAddress1.equals(sameEmailAddress2)).isTrue();
        assertThat(sameEmailAddress1.equals(differentEmailAddress)).isFalse();
    }
}
