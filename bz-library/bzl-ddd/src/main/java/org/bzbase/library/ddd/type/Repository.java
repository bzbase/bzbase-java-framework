package org.bzbase.library.ddd.type;

import java.util.Optional;

/**
 * 资源库接口，针对一个聚合根提供持久化服务
 *
 * @param <A> 聚合根类型
 * @param <I> 聚合根标识符类型
 *
 * @author legendjw
 */
public interface Repository<A extends AggregateRoot<I>, I> {
    /**
     * 根据聚合根标识查询指定的聚合根
     *
     * @param id 聚合根标识
     * @return 聚合根
     */
    Optional<A> findById(I id);

    /**
     * 保存一个聚合根
     *
     * @param aggregateRoot 聚合根
     */
    void save(A aggregateRoot);

    /**
     * 移除一个聚合根
     *
     * @param aggregateRoot 聚合根
     */
    void remove(A aggregateRoot);
}
