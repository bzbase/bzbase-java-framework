package org.bzbase.domain.tenant;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bzbase.domain.tenant.valueobject.TenantStatus;
import org.bzbase.library.ddd.type.AbstractAggregateRoot;
import org.bzbase.primitive.enterprise.EnterpriseProfile;
import org.bzbase.primitive.tenant.TenantId;

import java.time.Instant;

/**
 * 租户聚合根
 */
@Getter
@Setter
@Builder(toBuilder = true)
public class Tenant extends AbstractAggregateRoot<TenantId> {
    /**
     * 租户ID
     */
    private TenantId id;

    /**
     * 租户名称
     */
    private String name;

    /**
     * 企业档案，可为空
     */
    private EnterpriseProfile enterpriseProfile;

    /**
     * 租户状态
     */
    private TenantStatus status;

    /**
     * 创建时间
     */
    private Instant createdAt;

    /**
     * 启用租户
     */
    public void activate() {
        this.status = TenantStatus.ACTIVE;
    }

    /**
     * 停用租户
     */
    public void deactivate() {
        this.status = TenantStatus.INACTIVE;
    }
}
