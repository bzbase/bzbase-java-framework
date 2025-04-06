package org.bzbase.library.ddd.exception;

import lombok.Getter;

/**
 * 使用错误代码的异常
 *
 * @author legendjw
 */
@Getter
public class ErrorCodeException extends RuntimeException {
    /**
     * 错误代码
     */
    private final ErrorCode errorCode;

    public ErrorCodeException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCodeException(ErrorCode errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public ErrorCodeException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCodeException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}
