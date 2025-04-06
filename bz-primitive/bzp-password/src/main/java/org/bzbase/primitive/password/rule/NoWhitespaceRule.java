package org.bzbase.primitive.password.rule;

import lombok.NonNull;
import lombok.Value;
import org.bzbase.primitive.password.Module;

/**
 * 不允许空格密码规则
 *
 * @author legendjw
 */
@Value
public class NoWhitespaceRule implements PasswordRule {
    @Override
    public ValidationResult validate(@NonNull String plainPassword) {
        if (plainPassword.contains(" ")) {
            return ValidationResult.invalid(getNoWhitespaceErrorMessage());
        }
        return ValidationResult.valid();
    }

    private String getNoWhitespaceErrorMessage() {
        return Module.getMessageSource().getMessage("NoWhitespacePasswordRule.noWhitespaceError");
    }
}
