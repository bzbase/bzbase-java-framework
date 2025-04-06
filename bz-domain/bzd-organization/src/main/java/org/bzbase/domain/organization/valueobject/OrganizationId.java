package org.bzbase.domain.organization.valueobject;

import org.bzbase.library.ddd.type.AbstractId;

import lombok.EqualsAndHashCode;

/**
 * 组织聚合根唯一标识
 *
 * @author legendjw
 */
@EqualsAndHashCode(callSuper = true)
public class OrganizationId extends AbstractId {
    public OrganizationId(String value) {
        super(value);
    }
} 