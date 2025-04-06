package org.bzbase.library.persistence.changetracking;

import org.bzbase.library.ddd.type.Identifiable;
import org.bzbase.library.persistence.changetracking.diff.ObjectDiff;

import java.util.List;
import java.util.Optional;

/**
 * 变更追踪器接口
 *
 * @author legendjw
 */
public interface ChangeTracker<T extends Identifiable<ID>, ID> {
    /**
     * 追踪一个对象
     *
     * @param object 需要追踪的对象
     */
    void attach(T object);

    /**
     * 解除追踪一个对象
     *
     * @param object 需要解除追踪的对象
     */
    void detach(T object);

    /**
     * 清除所有追踪对象
     */
    void clear();

    /**
     * 检测对象变化
     *
     * @param object 需要比对的对象
     * @return 对象变化
     */
    ObjectDiff detectChanges(T object);

    /**
     * 根据ID查找正在追踪的对象快照
     *
     * @param id 对象ID
     * @return 追踪对象
     */
    Optional<T> find(ID id);

    /**
     * 查询所有正在追踪的对象快照
     *
     * @return 对象快照列表
     */
    List<T> find();
}
