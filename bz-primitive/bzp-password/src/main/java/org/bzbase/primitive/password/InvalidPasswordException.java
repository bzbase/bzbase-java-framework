package org.bzbase.primitive.password;

import org.bzbase.library.ddd.exception.DomainException;

/**
 * 无效的密码异常
 *
 * @author legendjw
 */
public class InvalidPasswordException extends DomainException {
    public InvalidPasswordException(String message) {
        super(message);
    }

    public InvalidPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
