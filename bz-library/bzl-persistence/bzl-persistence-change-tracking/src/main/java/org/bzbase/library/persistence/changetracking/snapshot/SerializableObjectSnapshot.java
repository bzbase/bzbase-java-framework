package org.bzbase.library.persistence.changetracking.snapshot;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

/**
 * 基于java序列化实现的对象快照，对象必须实现 Serializable 接口
 *
 * @author legendjw
 */
public class SerializableObjectSnapshot implements ObjectSnapshot {
    @Override
    public <T> T snapshot(T object) {
        if (!(object instanceof Serializable)) {
            throw new IllegalArgumentException(String.format("%s must implement the serializable interface", object.getClass().getName()));
        }
        return (T) SerializationUtils.clone((Serializable) object);
    }
}
