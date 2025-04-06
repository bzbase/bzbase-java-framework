package org.bzbase.domain.authentication.valueobject;

import lombok.Value;
import org.bzbase.library.ddd.type.Identifier;
import org.bzbase.library.ddd.type.ValueObject;

import java.io.Serializable;

/**
 * 认证id
 *
 * @author legendjw
 */
@Value
public class AuthenticationId implements ValueObject, Identifier, Serializable {
    String value;
}
