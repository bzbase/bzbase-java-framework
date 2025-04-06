package org.bzbase.domain.organization.service;

import org.bzbase.domain.organization.Department;
import org.bzbase.domain.organization.valueobject.DepartmentId;
import org.bzbase.domain.organization.valueobject.EmployeeId;
import org.bzbase.domain.organization.valueobject.OrganizationId;
import org.bzbase.primitive.user.UserId;

/**
 * 部门管理服务
 */
public interface DepartmentManageService {
    /**
     * 创建部门
     * 
     * @param organizationId 组织ID
     * @param name           部门名称
     * @param parentId       上级部门ID
     * @param managerId      部门主管ID
     * @param currentUserId  当前用户ID
     * @return 部门
     */
    Department createDepartment(OrganizationId organizationId, String name, DepartmentId parentId,
            EmployeeId managerId, UserId currentUserId);

    /**
     * 修改部门
     * 
     * @param departmentId  部门ID
     * @param name          部门名称
     * @param parentId      上级部门ID
     * @param managerId     部门主管ID
     * @param currentUserId 当前用户ID
     * @return 部门
     */
    Department modifyDepartment(DepartmentId departmentId, String name, DepartmentId parentId, EmployeeId managerId,
            UserId currentUserId);

    /**
     * 删除部门
     * 
     * @param departmentId  部门ID
     * @param currentUserId 当前用户ID
     * @return 部门
     */
    Department deleteDepartment(DepartmentId departmentId, UserId currentUserId);
}
