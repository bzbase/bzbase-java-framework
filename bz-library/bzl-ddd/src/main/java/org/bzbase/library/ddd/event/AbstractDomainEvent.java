package org.bzbase.library.ddd.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 抽象领域事件
 *
 * @author legendjw
 * @since 1.0.0
 */
@Getter
@EqualsAndHashCode
public abstract class AbstractDomainEvent implements DomainEvent, Serializable {
    /**
     * 事件id
     */
    protected String id;

    /**
     * 发生时间
     */
    protected Instant occurredAt;

    public AbstractDomainEvent(String id) {
        this.id = id;
        this.occurredAt = Instant.now();
    }

    public AbstractDomainEvent() {
        this.id = UUID.randomUUID().toString();
        this.occurredAt = Instant.now();
    }
}
