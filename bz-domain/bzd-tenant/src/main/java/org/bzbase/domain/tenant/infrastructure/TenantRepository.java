package org.bzbase.domain.tenant.infrastructure;

import org.bzbase.domain.tenant.Tenant;
import org.bzbase.library.ddd.type.Repository;
import org.bzbase.primitive.tenant.TenantId;

/**
 * 租户资源库
 */
public interface TenantRepository extends Repository<Tenant, TenantId> {
    /**
     * 检查租户名称是否存在
     *
     * @param name 租户名称
     * @return 是否存在
     */
    boolean existsByName(String name);
}
