package org.bzbase.library.ddd.exception;

import lombok.Getter;

/**
 * 应用领域异常
 *
 * @author legendjw
 */
@Getter
public class ApplicationDomainException extends ApplicationException {
    /**
     * 错误代码
     */
    private final String code;

    public ApplicationDomainException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ApplicationDomainException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
