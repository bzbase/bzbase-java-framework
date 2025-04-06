package org.bzbase.primitive.user;

import org.bzbase.library.ddd.type.AbstractId;

import lombok.EqualsAndHashCode;

/**
 * 用户唯一标识
 *
 * @author legendjw
 */
@EqualsAndHashCode(callSuper = true)
public class UserId extends AbstractId {
    public UserId(String value) {
        super(value);
    }
}
