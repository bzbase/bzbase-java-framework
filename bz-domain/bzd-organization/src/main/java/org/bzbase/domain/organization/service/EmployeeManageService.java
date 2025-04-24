package org.bzbase.domain.organization.service;

import java.time.LocalDate;

import org.bzbase.domain.organization.Employee;
import org.bzbase.domain.organization.valueobject.EmployeeId;

/**
 * 员工管理领域服务
 */
public interface EmployeeManageService {
    /**
     * 创建员工
     * 
     * @param employee 员工
     * @return 员工
     */
    Employee createEmployee(Employee employee);

    /**
     * 修改员工信息
     * 
     * @param employee 员工
     * @return 员工
     */
    Employee modifyEmployee(Employee employee);

    /**
     * 删除员工
     * 
     * @param employeeId 员工ID
     */
    Employee deleteEmployee(EmployeeId employeeId);

    /**
     * 员工入职
     * 
     * @param employeeId  员工ID
     * @param onboardDate 入职日期
     * @return 员工
     */
    Employee onboardEmployee(EmployeeId employeeId, LocalDate onboardDate);

    /**
     * 员工离职
     * 
     * @param employeeId   员工ID
     * @param offboardDate 离职日期
     * @param reason       离职原因
     * @return 员工
     */
    Employee offboardEmployee(EmployeeId employeeId, LocalDate offboardDate, String reason);
}
