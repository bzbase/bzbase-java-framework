package org.bzbase.library.ddd.exception;

import lombok.Getter;

/**
 * 领域异常
 *
 * @author legendjw
 */
@Getter
public class DomainException extends RuntimeException {
    /**
     * 错误码
     */
    private ErrorCode code;

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public DomainException(String message, ErrorCode code) {
        super(message);
        this.code = code;
    }

    public DomainException(String message, ErrorCode code, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public DomainException(ErrorCode code) {
        this.code = code;
    }

    public DomainException(ErrorCode code, Throwable cause) {
        super(cause);
        this.code = code;
    }
}
