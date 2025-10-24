package org.bzbase.domain.organization.infrastructure;

import org.bzbase.domain.organization.Organization;
import org.bzbase.library.ddd.type.Repository;
import org.bzbase.primitive.organization.OrganizationId;
import org.bzbase.primitive.user.UserId;

import java.util.List;

/**
 * 组织资源库
 *
 * @author legendjw
 */
public interface OrganizationRepository extends Repository<Organization, OrganizationId> {
    /**
     * 根据创建人查询组织
     *
     * @param createdBy 创建人
     * @return 组织列表
     */
    List<Organization> findByCreatedBy(UserId createdBy);

    /**
     * 检查组织名称是否存在
     *
     * @param name     组织名称
     * @return 是否存在
     */
    boolean existsByName(String name);
}