package org.bzbase.primitive.password.rule;

import lombok.NonNull;
import lombok.Value;

/**
 * 正则表达式规则
 *
 * @author legendjw
 */
@Value
public class RegexRule implements PasswordRule {
    /**
     * 正则表达式
     */
    String regex;

    /**
     * 不满足条件的错误信息
     */
    String errorMessage;

    /**
     * 匹配类型，可以是正面匹配符合这个正则表达式，也可以是负面匹配，即要求不符合这个正则表达式
     */
    MatchType matchType;

    public RegexRule(@NonNull String regex, @NonNull String errorMessage) {
        this.regex = regex;
        this.errorMessage = errorMessage;
        matchType = MatchType.POSITIVE;
    }

    public RegexRule(@NonNull String regex, @NonNull String errorMessage, @NonNull MatchType matchType) {
        this.regex = regex;
        this.errorMessage = errorMessage;
        this.matchType = matchType;
    }

    @Override
    public ValidationResult validate(@NonNull String plainPassword) {
        boolean matches = plainPassword.matches(regex);

        if ((matchType == MatchType.POSITIVE && !matches) || (matchType == MatchType.NEGATIVE && matches)) {
            return ValidationResult.invalid(errorMessage);
        }

        return ValidationResult.valid();
    }
}
