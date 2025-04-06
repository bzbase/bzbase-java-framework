package org.bzbase.library.persistence.changetracking.diff;

import lombok.Value;

/**
 * 对象改变字段
 *
 * @author legendjw
 */
@Value
public class ObjectChangedField {
    /**
     * 字段名
     */
    String field;

    /**
     * 旧的值
     */
    Object oldValue;

    /**
     * 新的值
     */
    Object newValue;
}
