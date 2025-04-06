package org.bzbase.library.persistence.changetracking.diff;

import lombok.Value;

/**
 * 集合更改对象
 *
 * @author legendjw
 */
@Value
public class CollectionChangedObject<T> {
    /**
     * 改变类型
     */
    ObjectChangedType changedType;

    /**
     * 旧的值
     */
    T oldValue;

    /**
     * 新的值
     */
    T newValue;
}
