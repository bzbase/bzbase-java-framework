package org.bzbase.domain.organization.service;

import org.bzbase.domain.organization.Organization;
import org.bzbase.primitive.organization.OrganizationId;

/**
 * 组织管理服务
 */
public interface OrganizationManageService {
    /**
     * 创建组织
     * 
     * @param organization 组织
     * @return 组织
     */
    Organization createOrganization(Organization organization);

    /**
     * 修改组织
     * 
     * @param organization 组织
     * @return 组织
     */
    Organization modifyOrganization(Organization organization);

    /**
     * 删除组织
     * 
     * @param id 组织ID
     * @return 组织
     */
    Organization deleteOrganization(OrganizationId id);

    /**
     * 启用组织
     * 
     * @param id 组织ID
     * @return 组织
     */
    Organization enableOrganization(OrganizationId id);

    /**
     * 停用组织
     * 
     * @param id 组织ID
     * @return 组织
     */
    Organization disableOrganization(OrganizationId id);
}
