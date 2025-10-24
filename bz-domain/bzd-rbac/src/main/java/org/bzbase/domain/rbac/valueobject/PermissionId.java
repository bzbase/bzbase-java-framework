package org.bzbase.domain.rbac.valueobject;

import lombok.EqualsAndHashCode;
import org.bzbase.library.ddd.type.AbstractId;

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