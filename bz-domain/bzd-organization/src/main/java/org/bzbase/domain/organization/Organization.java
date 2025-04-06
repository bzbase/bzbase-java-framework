package org.bzbase.domain.organization;

import org.bzbase.domain.organization.valueobject.OrganizationId;
import org.bzbase.domain.organization.valueobject.OrganizationStatus;
import org.bzbase.library.ddd.type.LifecycleAggregateRoot;
import org.bzbase.primitive.user.UserId;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * 组织聚合根
 */
@Getter
@SuperBuilder(toBuilder = true)
public class Organization extends LifecycleAggregateRoot<OrganizationId> {
    /**
     * 组织ID
     */
    private OrganizationId id;

    /**
     * 组织名称
     */
    private String name;

    /**
     * 组织简称
     */
    private String shortName;

    /**
     * 组织状态
     */
    private OrganizationStatus status;

    /**
     * 创建组织
     *
     * @param id            组织ID
     * @param name          组织名称
     * @param shortName     组织简称
     * @param currentUserId 当前用户ID
     * @return 组织
     */
    public static Organization create(OrganizationId id, String name, String shortName, UserId currentUserId) {
        Organization organization = Organization.builder()
                .id(id)
                .name(name)
                .shortName(shortName)
                .status(OrganizationStatus.ACTIVE)
                .build();
        organization.markAsCreated(currentUserId);
        return organization;
    }

    /**
     * 修改组织
     *
     * @param name          组织名称
     * @param shortName     组织简称
     * @param currentUserId 当前用户ID
     */
    public void modify(String name, String shortName, UserId currentUserId) {
        this.name = name;
        this.shortName = shortName;
        this.markAsUpdated(currentUserId);
    }

    /**
     * 删除组织
     *
     * @param currentUserId 当前用户ID
     */
    public void delete(UserId currentUserId) {
        this.markAsDeleted(currentUserId);
    }

    /**
     * 启用组织
     *
     * @param currentUserId 当前用户ID
     */
    public void enable(UserId currentUserId) {
        this.status = OrganizationStatus.ACTIVE;
        this.markAsUpdated(currentUserId);
    }

    /**
     * 停用组织
     *
     * @param currentUserId 当前用户ID
     */
    public void disable(UserId currentUserId) {
        this.status = OrganizationStatus.INACTIVE;
        this.markAsUpdated(currentUserId);
    }
}
