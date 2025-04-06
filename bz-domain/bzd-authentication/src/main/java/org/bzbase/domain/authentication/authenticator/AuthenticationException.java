package org.bzbase.domain.authentication.authenticator;

/**
 * 身份认证异常
 *
 * @author legendjw
 */
public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
