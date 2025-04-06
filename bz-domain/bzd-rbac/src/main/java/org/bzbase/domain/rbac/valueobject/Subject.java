package org.bzbase.domain.rbac.valueobject;

import java.io.Serializable;

import org.bzbase.library.ddd.type.ValueObject;

import lombok.Value;

/**
 * 主体值对象
 * 
 * @author legendjw
 */
@Value
public class Subject implements ValueObject, Serializable {
	/**
	 * 主体类型
	 */
	String type;

	/**
	 * 主体标识
	 */
	String identifier;
}
