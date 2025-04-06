package org.bzbase.primitive.password.rule;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 限制长度密码规则测试
 *
 * @author legendjw
 */
class LengthRuleTest {
    @Test
    void test_fixed_length_rule() {
        LengthRule rule = new LengthRule(6);

        assertThat(rule.validate("1234").isValid()).isFalse();
        assertThat(rule.validate("1234").isValid()).isFalse();
        assertThat(rule.validate("123456").isValid()).isTrue();
    }

    @Test
    void test_min_max_length_rule() {
        LengthRule rule = new LengthRule(6, 8);

        assertThat(rule.validate("1234").isValid()).isFalse();
        assertThat(rule.validate("123456789").isValid()).isFalse();
        assertThat(rule.validate("123456").isValid()).isTrue();
        assertThat(rule.validate("1234567").isValid()).isTrue();
        assertThat(rule.validate("12345678").isValid()).isTrue();
    }
}
