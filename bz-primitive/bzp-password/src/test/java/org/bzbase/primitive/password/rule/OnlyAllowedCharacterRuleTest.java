package org.bzbase.primitive.password.rule;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 仅允许包含指定字符的规则测试
 *
 * @author legendjw
 */
class OnlyAllowedCharacterRuleTest {
    @Test
    void test_only_allowed_character_rule() {
        OnlyAllowedCharacterRule rule = new OnlyAllowedCharacterRule("abc", "仅允许包含abc字母");

        assertThat(rule.validate("1234").isValid()).isFalse();
        assertThat(rule.validate("abcde").isValid()).isFalse();
        assertThat(rule.validate("abc").isValid()).isTrue();
        assertThat(rule.validate("aaaabbbb").isValid()).isTrue();
    }

    @Test
    void test_single_characters_rule() {
        OnlyAllowedCharacterRule rule = new OnlyAllowedCharacterRule(Characters.DIGITS, "仅允许包含数字");

        assertThat(rule.validate("abcde").isValid()).isFalse();
        assertThat(rule.validate("123abc").isValid()).isFalse();
        assertThat(rule.validate("1234567890").isValid()).isTrue();
    }

    @Test
    void test_multiple_characters_rule() {
        Set<Characters> characters = Stream.of(Characters.DIGITS, Characters.LETTERS).collect(Collectors.toSet());
        OnlyAllowedCharacterRule rule = new OnlyAllowedCharacterRule(characters, "仅允许包含数字以及字母");

        assertThat(rule.validate("@@####").isValid()).isFalse();
        assertThat(rule.validate("12345@").isValid()).isFalse();
        assertThat(rule.validate("abc#").isValid()).isFalse();
        assertThat(rule.validate("abc").isValid()).isTrue();
        assertThat(rule.validate("123abc").isValid()).isTrue();
    }
}
