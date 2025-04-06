package org.bzbase.library.ddd.event;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * 领域事件接口
 *
 * @author legendjw
 * @since 1.0.0
 */
public interface DomainEvent {
    /**
     * 获取唯一的事件ID
     *
     * @return 事件ID
     */
    String getId();

    /**
     * 时间发生的时间
     *
     * @return 发生的时间
     */
    Instant getOccurredAt();
}
