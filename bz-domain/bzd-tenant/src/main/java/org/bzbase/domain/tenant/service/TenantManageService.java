package org.bzbase.domain.tenant.service;

import org.bzbase.domain.tenant.Tenant;
import org.bzbase.primitive.tenant.TenantId;

/**
 * 租户管理服务
 */
public interface TenantManageService {
    /**
     * 创建租户
     *
     * @param tenant 租户
     * @return 租户
     */
    Tenant createTenant(Tenant tenant);

    /**
     * 修改租户
     *
     * @param tenant 租户
     * @return 租户
     */
    Tenant modifyTenant(Tenant tenant);

    /**
     * 删除租户
     *
     * @param id 租户ID
     * @return 租户
     */
    Tenant deleteTenant(TenantId id);

    /**
     * 启用租户
     *
     * @param id 租户ID
     * @return 租户
     */
    Tenant activateTenant(TenantId id);

    /**
     * 停用租户
     *
     * @param id 租户ID
     * @return 租户
     */
    Tenant deactivateTenant(TenantId id);
}
