package org.bzbase.domain.authentication.valueobject;

import lombok.Value;
import org.bzbase.library.ddd.type.ValueObject;

import java.io.Serializable;

/**
 * 失败信息
 *
 * @author legendjw
 */
@Value
public class FailureMessage implements ValueObject, Serializable {
    /**
     * 错误码
     */
    String code;

    /**
     * 错误信息
     */
    String message;
}
