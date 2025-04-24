package org.bzbase.domain.organization.infrastructure;

import java.util.Optional;
import java.util.List;

import org.bzbase.domain.organization.Position;
import org.bzbase.domain.organization.valueobject.PositionId;
import org.bzbase.library.ddd.type.Repository;
import org.bzbase.primitive.organization.OrganizationId;
import org.bzbase.primitive.tenant.TenantId;

/**
 * 岗位资源库
 */
public interface PositionRepository extends Repository<Position, PositionId> {
    /**
     * 根据岗位ID查询岗位
     *
     * @param tenantId       租户ID
     * @param organizationId 组织ID
     * @param id 岗位ID
     * @return 岗位
     */
    Optional<Position> findById(TenantId tenantId, OrganizationId organizationId, PositionId id);

    /**
     * 判断指定组织下是否存在指定名称的岗位
     *
     * @param tenantId       租户ID
     * @param organizationId 组织ID
     * @param name           岗位名称
     * @return 是否存在
     */
    boolean existsByName(TenantId tenantId, OrganizationId organizationId, String name);
    
    /**
     * 根据父岗位ID查找子岗位
     *
     * @param tenantId 租户ID
     * @param organizationId 组织ID
     * @param parentId       父岗位ID
     * @return 子岗位列表
     */
    List<Position> findByParentId(TenantId tenantId, OrganizationId organizationId, PositionId parentId);
    
    /**
     * 判断指定组织下是否存在指定父岗位ID的岗位
     *
     * @param tenantId 租户ID
     * @param organizationId 组织ID
     * @param parentId       父岗位ID
     * @return 是否存在
     */
    boolean existsByParentId(TenantId tenantId, OrganizationId organizationId, PositionId parentId);
}