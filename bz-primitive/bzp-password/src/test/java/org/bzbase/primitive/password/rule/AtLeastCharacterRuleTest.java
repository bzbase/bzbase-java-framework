package org.bzbase.primitive.password.rule;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 至少包含指定数量字符的规则测试
 *
 * @author legendjw
 */
class AtLeastCharacterRuleTest {
    @Test
    void test_at_least_character_rule() {
        AtLeastCharacterRule rule = new AtLeastCharacterRule(Characters.DIGITS, "至少包含一个数字");

        assertThat(rule.validate("abcde").isValid()).isFalse();
        assertThat(rule.validate("1abc").isValid()).isTrue();
        assertThat(rule.validate("12345").isValid()).isTrue();
    }

    @Test
    void test_at_least_count_character_rule() {
        AtLeastCharacterRule rule = new AtLeastCharacterRule(Characters.LETTERS, 2, "至少包含2个字母");

        assertThat(rule.validate("1234").isValid()).isFalse();
        assertThat(rule.validate("123a").isValid()).isFalse();
        assertThat(rule.validate("12ab").isValid()).isTrue();
        assertThat(rule.validate("abcd").isValid()).isTrue();
    }
}
