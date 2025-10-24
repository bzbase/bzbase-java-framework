package org.bzbase.domain.organization.valueobject;

import lombok.EqualsAndHashCode;
import org.bzbase.library.ddd.type.AbstractId;

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