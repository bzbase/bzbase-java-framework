package org.bzbase.library.persistence.changetracking.diff;

import lombok.Value;

/**
 * 映射对象变更
 *
 * @author legendjw
 */
@Value
public class MapChangedObject<K, T> {
    /**
     * 改变类型
     */
    ObjectChangedType changedType;

    /**
     * map键
     */
    K key;

    /**
     * 旧的值
     */
    T oldValue;

    /**
     * 新的值
     */
    T newValue;
}
