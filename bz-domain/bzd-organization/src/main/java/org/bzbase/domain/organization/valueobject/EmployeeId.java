package org.bzbase.domain.organization.valueobject;

import org.bzbase.library.ddd.type.AbstractId;

import lombok.EqualsAndHashCode;

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