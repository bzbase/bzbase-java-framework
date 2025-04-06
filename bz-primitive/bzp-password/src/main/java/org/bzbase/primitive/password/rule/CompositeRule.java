package org.bzbase.primitive.password.rule;

import lombok.NonNull;
import lombok.Value;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 组合密码规则，接受一组规则来验证密码是否符合策略
 *
 * @author legendjw
 */
@Value
public class CompositeRule implements PasswordRule {
    /**
     * 密码规则
     */
    Set<PasswordRule> rules;
    String errorMessage;

    public CompositeRule(PasswordRule... rules) {
        if (rules.length == 0) {
            throw new IllegalArgumentException("At least provide one rule");
        }
        this.rules = new HashSet<>(Arrays.asList(rules));
        this.errorMessage = "";
    }

    public CompositeRule(@NonNull Set<PasswordRule> rules, @NonNull String errorMessage) {
        if (rules.isEmpty()) {
            throw new IllegalArgumentException("At least provide one rule");
        }
        this.rules = rules;
        this.errorMessage = errorMessage;
    }

    @Override
    public ValidationResult validate(@NonNull String plainPassword) {
        for (PasswordRule rule : rules) {
            ValidationResult result = rule.validate(plainPassword);
            if (result.isInvalid()) {
                return ValidationResult.invalid(result.getErrorMessage());
            }
        }
        return ValidationResult.valid();
    }
}
