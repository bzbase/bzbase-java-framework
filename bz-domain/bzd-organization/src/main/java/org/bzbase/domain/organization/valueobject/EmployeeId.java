package org.bzbase.domain.organization.valueobject;

import lombok.EqualsAndHashCode;
import org.bzbase.library.ddd.type.AbstractId;

/**
 * 员工聚合根唯一标识
 *
 * @author legendjw
 */
@EqualsAndHashCode(callSuper = true)
public class EmployeeId extends AbstractId {
    public EmployeeId(String value) {
        super(value);
    }
} 