package org.bzbase.domain.organization.event;

import lombok.Getter;
import org.bzbase.domain.organization.Organization;
import org.bzbase.library.ddd.event.AbstractDomainEvent;
import org.bzbase.primitive.organization.OrganizationId;
import org.bzbase.primitive.tenant.TenantId;

import java.time.Instant;

/**
 * 组织创建领域事件
 */
@Getter
public class OrganizationCreatedEvent extends AbstractDomainEvent {
    private final TenantId tenantId;
    private final OrganizationId organizationId;
    private final String organizationName;

    public OrganizationCreatedEvent(Organization organization) {
        this.tenantId = organization.getTenantId();
        this.organizationId = organization.getId();
        this.organizationName = organization.getName();
        this.occurredAt = Instant.now();
    }
}
