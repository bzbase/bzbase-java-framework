package org.bzbase.domain.organization.valueobject;

import lombok.EqualsAndHashCode;
import org.bzbase.library.ddd.type.AbstractId;

/**
 * 部门聚合根唯一标识
 *
 * @author legendjw
 */
@EqualsAndHashCode(callSuper = true)
public class DepartmentId extends AbstractId {
    public DepartmentId(String value) {
        super(value);
    }
}