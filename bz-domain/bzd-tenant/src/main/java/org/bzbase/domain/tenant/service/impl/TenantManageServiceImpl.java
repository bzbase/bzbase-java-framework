package org.bzbase.domain.tenant.service.impl;

import lombok.RequiredArgsConstructor;
import org.bzbase.domain.tenant.Tenant;
import org.bzbase.domain.tenant.infrastructure.TenantRepository;
import org.bzbase.domain.tenant.service.TenantManageService;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.IdGenerator;
import org.bzbase.primitive.tenant.TenantId;

import java.time.Instant;

/**
 * 租户管理服务实现类
 */
@RequiredArgsConstructor
public class TenantManageServiceImpl implements TenantManageService {
    private final IdGenerator<String> idGenerator;
    private final TenantRepository tenantRepository;

    @Override
    public Tenant createTenant(Tenant tenant) {
        if (tenantRepository.existsByName(tenant.getName())) {
            throw new DomainException("租户名称已经存在");
        }

        if (tenant.getId() == null) {
            tenant.setId(new TenantId(idGenerator.generate()));
        }
        tenant.activate();
        if (tenant.getCreatedAt() == null) {
            tenant.setCreatedAt(Instant.now());
        }

        return tenant;
    }

    @Override
    public Tenant modifyTenant(Tenant tenant) {
        Tenant oldTenant = getTenantById(tenant.getId());

        boolean isNameChanged = !tenant.getName().equals(oldTenant.getName());
        if (isNameChanged && tenantRepository.existsByName(tenant.getName())) {
            throw new DomainException("租户名称已经存在");
        }

        return tenant;
    }

    @Override
    public Tenant deleteTenant(TenantId id) {
        Tenant tenant = getTenantById(id);

        return tenant;
    }

    @Override
    public Tenant activateTenant(TenantId id) {
        Tenant tenant = getTenantById(id);

        tenant.activate();

        return tenant;
    }

    @Override
    public Tenant deactivateTenant(TenantId id) {
        Tenant tenant = getTenantById(id);

        tenant.deactivate();

        return tenant;
    }

    private Tenant getTenantById(TenantId id) {
        return tenantRepository.findById(id).orElseThrow(() -> new DomainException("指定的租户不存在"));
    }
}
