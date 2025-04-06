package org.bzbase.library.ddd.exception;

/**
 * 应用基础设施异常
 *
 * @author legendjw
 */
public class ApplicationInfrastructureException extends ApplicationException {
    public ApplicationInfrastructureException(String message) {
        super(message);
    }

    public ApplicationInfrastructureException(String message, Throwable cause) {
        super(message, cause);
    }
}
