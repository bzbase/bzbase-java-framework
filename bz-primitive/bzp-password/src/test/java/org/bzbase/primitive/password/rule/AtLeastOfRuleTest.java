package org.bzbase.primitive.password.rule;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 至少满足给定规则中的几组规则测试
 *
 * @author legendjw
 */
class AtLeastOfRuleTest {
    @Test
    void test_at_least_of_rule() {
        Set<PasswordRule> rules = Stream.of(
                new AtLeastCharacterRule(Characters.DIGITS, "至少包含一个数字"),
                new AtLeastCharacterRule(Characters.LOWERCASE_LETTERS, "至少包含一个小写字母"),
                new AtLeastCharacterRule(Characters.UPPERCASE_LETTERS, "至少包含一个大写字母")
        ).collect(Collectors.toSet());

        AtLeastOfRule rule = new AtLeastOfRule(rules, 2, "至少包含其中以下两种字符：数字，大写字母，小写字母");

        assertThat(rule.validate("abcde").isValid()).isFalse();
        assertThat(rule.validate("12345").isValid()).isFalse();
        assertThat(rule.validate("ABCDE").isValid()).isFalse();
        assertThat(rule.validate("1abc").isValid()).isTrue();
        assertThat(rule.validate("1ABC").isValid()).isTrue();
        assertThat(rule.validate("ABcd").isValid()).isTrue();
    }
}
