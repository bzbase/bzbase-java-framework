package org.bzbase.primitive.password.rule;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 正则表达式规则测试
 *
 * @author legendjw
 */
class RegexRuleTest {
    @Test
    void test_regex_rule() {
        RegexRule rule = new RegexRule("^\\d{6,10}$", "格式要求6-10位数字");

        assertThat(rule.validate("abcd").isValid()).isFalse();
        assertThat(rule.validate("abcd12").getErrorMessage()).isEqualTo("格式要求6-10位数字");
        assertThat(rule.validate("12345").isValid()).isFalse();
        assertThat(rule.validate("123456").isValid()).isTrue();
        assertThat(rule.validate("12345678").isValid()).isTrue();
    }

    @Test
    void test_negative_regex_rule() {
        RegexRule rule = new RegexRule("^\\d{6,10}$", "格式要求6-10位数字", MatchType.NEGATIVE);

        assertThat(rule.validate("abcd").isValid()).isTrue();
        assertThat(rule.validate("12345").isValid()).isTrue();
        assertThat(rule.validate("123456").isValid()).isFalse();
        assertThat(rule.validate("12345678").isValid()).isFalse();
    }
}
