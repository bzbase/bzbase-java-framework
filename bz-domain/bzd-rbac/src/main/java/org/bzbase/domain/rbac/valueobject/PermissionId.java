package org.bzbase.domain.rbac.valueobject;

import org.bzbase.library.ddd.type.AbstractId;

import lombok.EqualsAndHashCode;

/**
 * 权限聚合根唯一标识
 *
 * @author legendjw
 */
@EqualsAndHashCode(callSuper = true)
public class PermissionId extends AbstractId {
    public PermissionId(String value) {
        super(value);
    }
}