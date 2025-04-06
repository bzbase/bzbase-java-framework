package org.bzbase.domain.organization.service;

import org.bzbase.domain.organization.Organization;
import org.bzbase.domain.organization.valueobject.OrganizationId;
import org.bzbase.primitive.user.UserId;

/**
 * 组织管理服务
 */
public interface OrganizationManageService {
    /**
     * 创建组织
     * 
     * @param name          组织名称
     * @param shortName     组织简称
     * @param currentUserId 当前用户ID
     * @return 组织
     */
    Organization createOrganization(String name, String shortName, UserId currentUserId);

    /**
     * 修改组织
     * 
     * @param id            组织ID
     * @param name          组织名称
     * @param shortName     组织简称
     * @param currentUserId 当前用户ID
     * @return 组织
     */
    Organization modifyOrganization(OrganizationId id, String name, String shortName, UserId currentUserId);

    /**
     * 删除组织
     * 
     * @param id            组织ID
     * @param currentUserId 当前用户ID
     * @return 组织
     */
    Organization deleteOrganization(OrganizationId id, UserId currentUserId);

    /**
     * 启用组织
     * 
     * @param id            组织ID
     * @param currentUserId 当前用户ID
     * @return 组织
     */
    Organization enableOrganization(OrganizationId id, UserId currentUserId);

    /**
     * 停用组织
     * 
     * @param id            组织ID
     * @param currentUserId 当前用户ID
     * @return 组织
     */
    Organization disableOrganization(OrganizationId id, UserId currentUserId);
}
