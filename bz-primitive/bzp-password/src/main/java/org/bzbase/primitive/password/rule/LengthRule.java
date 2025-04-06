package org.bzbase.primitive.password.rule;

import lombok.NonNull;
import lombok.Value;
import org.bzbase.primitive.password.Module;

/**
 * 限制长度密码规则
 *
 * @author legendjw
 */
@Value
public class LengthRule implements PasswordRule {
    int minLength;
    int maxLength;

    public LengthRule(int length) {
        this(length, length);
    }

    public LengthRule(int minLength, int maxLength) {
        if (minLength > maxLength) {
            throw new IllegalArgumentException("minLength must be less than or equal to maxLength");
        }
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    public ValidationResult validate(@NonNull String plainPassword) {
        int length = plainPassword.length();
        boolean isFixedLength = minLength == maxLength;

        if (length < minLength) {
            String errorMessage = isFixedLength ? getFixedLengthErrorMessage() : getMinLengthErrorMessage();
            return ValidationResult.invalid(errorMessage);
        }

        if (length > maxLength) {
            String errorMessage = isFixedLength ? getFixedLengthErrorMessage() : getMaxLengthErrorMessage();
            return ValidationResult.invalid(errorMessage);
        }

        return ValidationResult.valid();
    }

    private String getMaxLengthErrorMessage() {
        return Module.getMessageSource().getMessage("LengthPasswordRule.maxLengthError", new Object[]{maxLength});
    }

    private String getMinLengthErrorMessage() {
        return Module.getMessageSource().getMessage("LengthPasswordRule.minLengthError", new Object[]{minLength});
    }

    private String getFixedLengthErrorMessage() {
        return Module.getMessageSource().getMessage("LengthPasswordRule.fixedLengthError", new Object[]{minLength});
    }
}
