package org.bzbase.primitive.password.rule;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 不允许空格密码规则测试
 *
 * @author legendjw
 */
class NoWhitespaceRuleTest {
    @Test
    void test_no_whitespace_rule() {
        NoWhitespaceRule rule = new NoWhitespaceRule();

        assertThat(rule.validate(" abcd").isValid()).isFalse();
        assertThat(rule.validate("a bcd").isValid()).isFalse();
        assertThat(rule.validate("a  bcd").isValid()).isFalse();
        assertThat(rule.validate("12345678").isValid()).isTrue();
    }
}
