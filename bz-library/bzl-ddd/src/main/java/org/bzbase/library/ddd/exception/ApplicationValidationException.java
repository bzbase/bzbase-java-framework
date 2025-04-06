package org.bzbase.library.ddd.exception;

/**
 * 应用验证异常
 *
 * @author legendjw
 */
public class ApplicationValidationException extends ApplicationException {
    public ApplicationValidationException(String message) {
        super(message);
    }

    public ApplicationValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
