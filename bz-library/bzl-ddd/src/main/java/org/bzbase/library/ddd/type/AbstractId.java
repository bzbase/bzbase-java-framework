package org.bzbase.library.ddd.type;

import lombok.EqualsAndHashCode;

/**
 * 抽象标识类
 * 所有聚合根的标识都应该继承此类
 *
 * @author legendjw
 */
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractId extends StringSingleValueObject implements Identifier {
    /**
     * 构造函数
     * 
     * @param value 值
     */
    protected AbstractId(String value) {
        super(value);
    }
}