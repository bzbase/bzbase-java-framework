package org.bzbase.library.ddd.type;

import java.util.ArrayList;
import java.util.List;

import org.bzbase.library.ddd.event.DomainEvent;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 实现DDD领域聚合根接口的抽象聚合根，所有的聚合根类可以继承自此抽象类来获取通用实现
 *
 * @param <I> 标识符类，常用String、Long或者自定义值对象类
 *
 * @author legendjw
 */
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class AbstractAggregateRoot<I> extends AbstractEntity<I> implements AggregateRoot<I> {
    /**
     * 领域事件
     */
    @Builder.Default
    protected final List<DomainEvent> domainEvents = new ArrayList<>();

    /**
     * 注册一个领域事件
     *
     * @param domainEvent 领域事件
     */
    public void registerDomainEvent(DomainEvent domainEvent) {
        this.domainEvents.add(domainEvent);
    }

    /**
     * 清除所有领域事件
     */
    public void clearDomainEvents() {
        this.domainEvents.clear();
    }
}
