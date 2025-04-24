package org.bzbase.domain.organization.infrastructure;

import java.util.List;
import java.util.Optional;

import org.bzbase.domain.organization.Organization;
import org.bzbase.library.ddd.type.Repository;
import org.bzbase.primitive.organization.OrganizationId;
import org.bzbase.primitive.tenant.TenantId;
import org.bzbase.primitive.user.UserId;

/**
 * 组织资源库
 *
 * @author legendjw
 */
public interface OrganizationRepository extends Repository<Organization, OrganizationId> {
    /**
     * 根据组织ID查询组织
     *
     * @param tenantId       租户ID
     * @param organizationId 组织ID
     * @return 组织
     */
    Optional<Organization> findById(TenantId tenantId, OrganizationId organizationId);

    /**
     * 根据创建人查询组织
     *
     * @param tenantId 租户ID
     * @param createdBy 创建人
     * @return 组织列表
     */
    List<Organization> findByCreatedBy(TenantId tenantId, UserId createdBy);

    /**
     * 检查组织名称是否存在
     *
     * @param tenantId 租户ID
     * @param name     组织名称
     * @return 是否存在
     */
    boolean existsByName(TenantId tenantId, String name);
}