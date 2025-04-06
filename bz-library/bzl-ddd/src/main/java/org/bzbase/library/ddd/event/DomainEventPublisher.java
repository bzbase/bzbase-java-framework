package org.bzbase.library.ddd.event;

import org.bzbase.library.ddd.type.AbstractAggregateRoot;

/**
 * 领域事件发布器
 *
 * @author legendjw
 * @since 1.0.0
 */
public interface DomainEventPublisher {
    /**
     * 发布领域事件
     *
     * @param event 事件
     */
    void publishEvent(DomainEvent event);

    /**
     * 发布聚合领域事件
     *
     * @param aggregateRoot 聚合根
     */
    void publishEvent(AbstractAggregateRoot aggregateRoot);
}
