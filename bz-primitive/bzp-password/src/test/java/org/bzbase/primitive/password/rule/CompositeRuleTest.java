package org.bzbase.primitive.password.rule;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 组合密码规则测试
 *
 * @author legendjw
 */
class CompositeRuleTest {
    @Test
    void test_composite_rule() {
        CompositeRule rule = new CompositeRule(
                new LengthRule(6, 8),
                new OnlyAllowedCharacterRule(Characters.LETTERS, "只允许字母")
        );

        assertThat(rule.validate("12ab").isValid()).isFalse();
        assertThat(rule.validate("123456").isValid()).isFalse();
        assertThat(rule.validate("123abc").isValid()).isFalse();
        assertThat(rule.validate("abcd").isValid()).isFalse();
        assertThat(rule.validate("abcEFG").isValid()).isTrue();
    }
}
