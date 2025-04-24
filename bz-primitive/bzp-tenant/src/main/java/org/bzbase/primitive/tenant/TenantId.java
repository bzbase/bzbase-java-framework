package org.bzbase.primitive.tenant;

import org.bzbase.library.ddd.type.AbstractId;

import lombok.EqualsAndHashCode;

/**
 * 租户唯一标识
 *
 * @author legendjw
 */
@EqualsAndHashCode(callSuper = true)
public class TenantId extends AbstractId {
    public TenantId(String value) {
        super(value);
    }
}
