package org.bzbase.primitive.password.rule;

import lombok.NonNull;
import lombok.Value;

import java.util.Set;

/**
 * 至少满足给定规则中的几组规则，使用场景比如至少包含以下两类字符：字母、数字、特殊字符
 *
 * @author legendjw
 */
@Value
public class AtLeastOfRule implements PasswordRule {
    Set<PasswordRule> rules;
    int numberOfRules;
    String errorMessage;

    public AtLeastOfRule(@NonNull Set<PasswordRule> rules, int numberOfRules) {
        this(rules, numberOfRules, "");
    }

    public AtLeastOfRule(@NonNull Set<PasswordRule> rules, int numberOfRules, String errorMessage) {
        if (rules.isEmpty()) {
            throw new IllegalArgumentException("rules cannot be empty");
        }
        if (numberOfRules < 1) {
            throw new IllegalArgumentException("numberOfRules must be greater than 0");
        }
        if (numberOfRules > rules.size()) {
            throw new IllegalArgumentException("numberOfRules must be less than or equal to the number of rules");
        }
        this.rules = rules;
        this.numberOfRules = numberOfRules;
        this.errorMessage = errorMessage;
    }

    @Override
    public ValidationResult validate(@NonNull String plainPassword) {
        int satisfiedCount = 0;
        for (PasswordRule rule : rules) {
            if (rule.validate(plainPassword).isValid()) {
                satisfiedCount++;
                if (satisfiedCount >= numberOfRules) {
                    return ValidationResult.valid();
                }
            }
        }
        return ValidationResult.invalid(errorMessage);
    }
}
