package org.bzbase.library.persistence.changetracking.snapshot;

/**
 * 对象快照接口
 *
 * @author legendjw
 */
public interface ObjectSnapshot {
    /**
     * 创建指定对象的快照
     *
     * @param object 需要创建快照的对象
     * @param <T> 指定对象类型
     * @return 对象快照
     */
    <T> T snapshot(T object);
}
