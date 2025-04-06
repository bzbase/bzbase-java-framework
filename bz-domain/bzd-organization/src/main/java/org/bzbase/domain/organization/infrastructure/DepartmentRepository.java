package org.bzbase.domain.organization.infrastructure;

import java.util.List;

import org.bzbase.domain.organization.Department;
import org.bzbase.domain.organization.valueobject.DepartmentId;
import org.bzbase.domain.organization.valueobject.OrganizationId;
import org.bzbase.library.ddd.type.Repository;

/**
 * 部门资源库
 *
 * @author legendjw
 */
public interface DepartmentRepository extends Repository<Department, DepartmentId> {
    /**
     * 根据组织ID查找所有部门
     *
     * @param organizationId 组织ID
     * @return 部门列表
     */
    List<Department> findByOrganizationId(OrganizationId organizationId);

    /**
     * 根据父部门ID查找子部门
     *
     * @param organizationId 组织ID
     * @param parentId       父部门ID
     * @return 子部门列表
     */
    List<Department> findByOrganizationIdAndParentId(OrganizationId organizationId, DepartmentId parentId);

    /**
     * 判断指定组织下是否存在指定名称的部门
     *
     * @param organizationId 组织ID
     * @param name           部门名称
     * @return 是否存在
     */
    boolean existsByOrganizationIdAndName(OrganizationId organizationId, String name);

    /**
     * 判断指定组织下是否存在指定父部门ID的部门
     *
     * @param organizationId 组织ID
     * @param parentId       父部门ID
     * @return 是否存在
     */
    boolean existsByOrganizationIdAndParentId(OrganizationId organizationId, DepartmentId parentId);
}