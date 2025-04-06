package org.bzbase.domain.organization.valueobject;

import org.bzbase.library.ddd.type.AbstractId;

import lombok.EqualsAndHashCode;

/**
 * 岗位ID
 */
@EqualsAndHashCode(callSuper = true)
public class PositionId extends AbstractId {
	public PositionId(String value) {
		super(value);
	}
}