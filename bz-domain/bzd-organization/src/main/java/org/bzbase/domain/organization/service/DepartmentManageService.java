package org.bzbase.domain.organization.service;

import org.bzbase.domain.organization.Department;
import org.bzbase.domain.organization.valueobject.DepartmentId;

/**
 * 部门管理服务
 */
public interface DepartmentManageService {
    /**
     * 创建部门
     * 
     * @param department 部门
     * @return 部门
     */
    Department createDepartment(Department department);

    /**
     * 修改部门
     * 
     * @param department 部门
     * @return 部门
     */
    Department modifyDepartment(Department department);

    /**
     * 删除部门
     * 
     * @param departmentId 部门ID
     * @return 部门
     */
    Department deleteDepartment(DepartmentId departmentId);
}
