package org.bzbase.domain.rbac.valueobject;

import org.bzbase.library.ddd.type.AbstractId;

import lombok.EqualsAndHashCode;

/**
 * 角色聚合根唯一标识
 *
 * @author legendjw
 */
@EqualsAndHashCode(callSuper = true)
public class RoleId extends AbstractId {
    public RoleId(String value) {
        super(value);
    }
}