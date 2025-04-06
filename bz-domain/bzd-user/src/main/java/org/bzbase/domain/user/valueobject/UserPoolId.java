package org.bzbase.domain.user.valueobject;

import org.bzbase.library.ddd.type.AbstractId;

import lombok.EqualsAndHashCode;

/**
 * 用户持聚合根唯一标示
 *
 * @author legendjw
 */
@EqualsAndHashCode(callSuper = true)
public class UserPoolId extends AbstractId {
    public UserPoolId(String value) {
        super(value);
    }
}
