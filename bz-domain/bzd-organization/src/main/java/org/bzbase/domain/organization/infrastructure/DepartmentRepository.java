package org.bzbase.domain.organization.infrastructure;

import java.util.List;
import java.util.Optional;

import org.bzbase.domain.organization.Department;
import org.bzbase.domain.organization.valueobject.DepartmentId;
import org.bzbase.library.ddd.type.Repository;
import org.bzbase.primitive.organization.OrganizationId;
import org.bzbase.primitive.tenant.TenantId;

/**
 * 部门资源库
 *
 * @author legendjw
 */
public interface DepartmentRepository extends Repository<Department, DepartmentId> {
    /**
     * 根据部门ID查询部门
     *
     * @param tenantId 租户ID
     * @param organizationId 组织ID
     * @param id 部门ID
     * @return 部门
     */
    Optional<Department> findById(TenantId tenantId, OrganizationId organizationId, DepartmentId id);

    /**
     * 根据组织ID查找所有部门
     *
     * @param tenantId 租户ID
     * @param organizationId 组织ID
     * @return 部门列表
     */
    List<Department> findByOrganizationId(TenantId tenantId, OrganizationId organizationId);

    /**
     * 根据父部门ID查找子部门
     *
     * @param tenantId 租户ID
     * @param organizationId 组织ID
     * @param parentId       父部门ID
     * @return 子部门列表
     */
    List<Department> findByParentId(TenantId tenantId, OrganizationId organizationId, DepartmentId parentId);

    /**
     * 判断指定组织下是否存在指定名称的部门
     *
     * @param organizationId 组织ID
     * @param name           部门名称
     * @return 是否存在
     */
    boolean existsByName(TenantId tenantId, OrganizationId organizationId, String name);

    /**
     * 判断指定组织下是否存在指定父部门ID的部门
     *
     * @param organizationId 组织ID
     * @param parentId       父部门ID
     * @return 是否存在
     */
    boolean existsByParentId(TenantId tenantId, OrganizationId organizationId, DepartmentId parentId);
}