package org.bzbase.primitive.password.rule;

import org.bzbase.primitive.password.rule.CommonPasswordPolicy;
import org.bzbase.primitive.password.rule.PasswordRule;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 常用的密码策略测试
 *
 * @author legendjw
 */
class CommonPasswordPolicyTest {
    @Test
    void test_six_digit_rule() {
        PasswordRule rule = CommonPasswordPolicy.SIX_DIGIT;

        assertThat(rule.validate("abcde").isValid()).isFalse();
        assertThat(rule.validate("123").isValid()).isFalse();
        assertThat(rule.validate("123456").isValid()).isTrue();
    }

    @Test
    void test_at_least_two_of_letter_digit_special_rule() {
        PasswordRule rule = CommonPasswordPolicy.AT_LEAST_TWO_OF_LETTER_DIGIT_SPECIAL;

        assertThat(rule.validate("12345678").isValid()).isFalse();
        assertThat(rule.validate("abcdefgh").isValid()).isFalse();
        assertThat(rule.validate("1234abcd").isValid()).isTrue();
        assertThat(rule.validate("1234####").isValid()).isTrue();
        assertThat(rule.validate("abc123#$").isValid()).isTrue();
    }

    @Test
    void test_at_least_three_of_upper_lower_digit_special_rule() {
        PasswordRule rule = CommonPasswordPolicy.AT_LEAST_THREE_OF_UPPER_LOWER_DIGIT_SPECIAL;

        assertThat(rule.validate("12345678").isValid()).isFalse();
        assertThat(rule.validate("abcdefgh").isValid()).isFalse();
        assertThat(rule.validate("1234abcd").isValid()).isFalse();
        assertThat(rule.validate("abcd####").isValid()).isFalse();
        assertThat(rule.validate("abc123#$").isValid()).isTrue();
        assertThat(rule.validate("Abcd1234").isValid()).isTrue();
        assertThat(rule.validate("Abcd123#").isValid()).isTrue();
    }

    @Test
    void test_at_least_one_letter_digit_rule() {
        PasswordRule rule = CommonPasswordPolicy.AT_LEAST_ONE_LETTER_DIGIT;

        assertThat(rule.validate("12345678").isValid()).isFalse();
        assertThat(rule.validate("abcdefgh").isValid()).isFalse();
        assertThat(rule.validate("1234####").isValid()).isFalse();
        assertThat(rule.validate("1234abcd").isValid()).isTrue();
        assertThat(rule.validate("Abcd1234").isValid()).isTrue();
    }

    @Test
    void test_at_least_one_upper_lower_digit_rule() {
        PasswordRule rule = CommonPasswordPolicy.AT_LEAST_ONE_UPPER_LOWER_DIGIT;

        assertThat(rule.validate("12345678").isValid()).isFalse();
        assertThat(rule.validate("abcdefgh").isValid()).isFalse();
        assertThat(rule.validate("1234####").isValid()).isFalse();
        assertThat(rule.validate("1234abcd").isValid()).isFalse();
        assertThat(rule.validate("Abcd1234").isValid()).isTrue();
    }

    @Test
    void test_at_least_one_letter_digit_special_rule() {
        PasswordRule rule = CommonPasswordPolicy.AT_LEAST_ONE_LETTER_DIGIT_SPECIAL;

        assertThat(rule.validate("12345678").isValid()).isFalse();
        assertThat(rule.validate("abcdefgh").isValid()).isFalse();
        assertThat(rule.validate("1234####").isValid()).isFalse();
        assertThat(rule.validate("1234abcd").isValid()).isFalse();
        assertThat(rule.validate("Abcd1234").isValid()).isFalse();
        assertThat(rule.validate("abcd1234#").isValid()).isTrue();
    }

    @Test
    void test_at_least_one_upper_lower_digit_special_rule() {
        PasswordRule rule = CommonPasswordPolicy.AT_LEAST_ONE_UPPER_LOWER_DIGIT_SPECIAL;

        assertThat(rule.validate("12345678").isValid()).isFalse();
        assertThat(rule.validate("abcdefgh").isValid()).isFalse();
        assertThat(rule.validate("1234####").isValid()).isFalse();
        assertThat(rule.validate("1234abcd").isValid()).isFalse();
        assertThat(rule.validate("abcd1234#").isValid()).isFalse();
        assertThat(rule.validate("Abcd1234#").isValid()).isTrue();
    }
}
