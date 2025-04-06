package org.bzbase.domain.organization.infrastructure;

import org.bzbase.domain.organization.Position;
import org.bzbase.domain.organization.valueobject.OrganizationId;
import org.bzbase.domain.organization.valueobject.PositionId;
import org.bzbase.library.ddd.type.Repository;

/**
 * 岗位资源库
 */
public interface PositionRepository extends Repository<Position, PositionId> {
    /**
     * 判断指定组织下是否存在指定名称的岗位
     * 
     * @param organizationId 组织ID
     * @param name           岗位名称
     * @return 是否存在
     */
    boolean existsByOrganizationIdAndName(OrganizationId organizationId, String name);
}