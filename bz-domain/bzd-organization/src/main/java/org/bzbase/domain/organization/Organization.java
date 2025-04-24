package org.bzbase.domain.organization;

import java.time.Instant;

import org.bzbase.domain.organization.valueobject.OrganizationStatus;
import org.bzbase.library.ddd.type.AbstractAggregateRoot;
import org.bzbase.primitive.organization.OrganizationId;
import org.bzbase.primitive.tenant.TenantId;
import org.bzbase.primitive.user.UserId;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 组织聚合根
 */
@Getter
@Setter
@Builder(toBuilder = true)
public class Organization extends AbstractAggregateRoot<OrganizationId> {
    /**
     * 组织ID
     */
    private OrganizationId id;

    /**
     * 租户ID
     */
	private TenantId tenantId;

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
     * 创建人
     */
	private UserId createdBy;

	/**
	 * 创建时间
	 */
	private Instant createdAt;

    /**
     * 启用组织
     */
    public void enable() {
        this.status = OrganizationStatus.ACTIVE;
    }

    /**
     * 停用组织
     */
    public void disable() {
        this.status = OrganizationStatus.INACTIVE;
    }
}
