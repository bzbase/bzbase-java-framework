package org.bzbase.library.ddd.type;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 实现DDD领域聚合根接口的包含生命周期的抽象聚合根，所有的聚合根类可以继承自此抽象类来获取通用实现
 *
 * @param <I> 标识符类，常用String、Long或者自定义值对象类
 *
 * @author legendjw
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class LifecycleAggregateRoot<I> extends AbstractAggregateRoot<I> {
    /**
     * 创建时间
     */
    private Instant createdAt;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 更新时间
     */
    private Instant updatedAt;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 删除时间
     */
    private Instant deletedAt;

    /**
     * 删除人
     */
    private String deletedBy;

    /**
     * 是否已删除
     */
    private boolean deleted;

    /**
     * 版本号
     */
    private Long version;

    /**
     * 标记为已创建
     */
    public void markAsCreated() {
        this.createdAt = Instant.now();
    }

    /**
     * 标记为已创建
     *
     * @param currentUserId 当前用户ID
     */
    public void markAsCreated(String currentUserId) {
        this.createdAt = Instant.now();
        this.createdBy = currentUserId;
    }

    /**
     * 标记为已创建
     *
     * @param currentUserId 当前用户ID
     */
    public void markAsCreated(AbstractId currentUserId) {
        markAsCreated(currentUserId != null ? currentUserId.getValue() : null);
    }

    /**
     * 标记为已更新
     */
    public void markAsUpdated() {
        this.updatedAt = Instant.now();
    }

    /**
     * 标记为已更新
     *
     * @param currentUserId 当前用户ID
     */
    public void markAsUpdated(String currentUserId) {
        this.updatedAt = Instant.now();
        this.updatedBy = currentUserId;
    }

    /**
     * 标记为已更新
     *
     * @param currentUserId 当前用户ID
     */
    public void markAsUpdated(AbstractId currentUserId) {
        markAsUpdated(currentUserId != null ? currentUserId.getValue() : null);
    }

    /**
     * 标记为已删除
     */
    public void markAsDeleted() {
        this.deletedAt = Instant.now();
        this.deleted = true;
    }

    /**
     * 标记为已删除
     *
     * @param currentUserId 当前用户ID
     */
    public void markAsDeleted(String currentUserId) {
        this.deletedAt = Instant.now();
        this.deletedBy = currentUserId;
        this.deleted = true;
    }

    /**
     * 标记为已删除
     *
     * @param currentUserId 当前用户ID
     */
    public void markAsDeleted(AbstractId currentUserId) {
        markAsDeleted(currentUserId != null ? currentUserId.getValue() : null);
    }
}
