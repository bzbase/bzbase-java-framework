package org.bzbase.primitive.password.rule;

import lombok.Value;

/**
 * 验证结果
 *
 * @author legendjw
 */
@Value
public class ValidationResult {
    boolean valid;
    String errorMessage;

    private ValidationResult(boolean valid, String errorMessage) {
        this.valid = valid;
        this.errorMessage = errorMessage;
    }

    public static ValidationResult valid() {
        return new ValidationResult(true, null);
    }

    public static ValidationResult invalid(String errorMessage) {
        return new ValidationResult(false, errorMessage);
    }

    public boolean isInvalid() {
        return !valid;
    }
}
