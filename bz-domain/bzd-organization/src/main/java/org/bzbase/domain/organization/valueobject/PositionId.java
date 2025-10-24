package org.bzbase.domain.organization.valueobject;

import lombok.EqualsAndHashCode;
import org.bzbase.library.ddd.type.AbstractId;

/**
 * 岗位ID
 */
@EqualsAndHashCode(callSuper = true)
public class PositionId extends AbstractId {
	public PositionId(String value) {
		super(value);
	}
}