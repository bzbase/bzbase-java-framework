package org.bzbase.library.ddd.event;

/**
 * 事件存储
 *
 * @author legendjw
 * @since 1.0.0
 */
public interface EventStore {
    /**
     * 添加一个事件
     *
     * @param event
     */
    void addEvent(DomainEvent event);
}
