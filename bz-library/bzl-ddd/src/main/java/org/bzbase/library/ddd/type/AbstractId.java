package org.bzbase.library.ddd.type;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

/**
 * 抽象标识类
 * 所有聚合根的标识都应该继承此类
 *
 * @author legendjw
 */
@Getter
@EqualsAndHashCode
public abstract class AbstractId implements ValueObject, Identifier, Serializable {
    protected final String value;

    protected AbstractId(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}