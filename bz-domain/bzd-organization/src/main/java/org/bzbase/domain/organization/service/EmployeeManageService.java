package org.bzbase.domain.organization.service;

import java.time.LocalDate;
import java.util.List;

import org.bzbase.domain.organization.Employee;
import org.bzbase.domain.organization.valueobject.EmergencyContact;
import org.bzbase.domain.organization.valueobject.EmployeeBasicInfo;
import org.bzbase.domain.organization.valueobject.EmployeeCredentialInfo;
import org.bzbase.domain.organization.valueobject.EmployeeId;
import org.bzbase.domain.organization.valueobject.EmployeeJobInfo;
import org.bzbase.domain.organization.valueobject.OrganizationId;
import org.bzbase.primitive.user.UserId;

/**
 * 员工管理领域服务
 */
public interface EmployeeManageService {
    /**
     * 创建员工
     * 
     * @param organizationId    组织ID
     * @param basicInfo         员工基本信息
     * @param jobInfo           员工工作信息
     * @param credentialInfo    员工证件信息
     * @param emergencyContacts 紧急联系人
     * @param currentUserId     当前用户ID
     * @return 员工
     */
    Employee createEmployee(OrganizationId organizationId, EmployeeBasicInfo basicInfo, EmployeeJobInfo jobInfo,
            EmployeeCredentialInfo credentialInfo, List<EmergencyContact> emergencyContacts,
            UserId currentUserId);

    /**
     * 修改员工信息
     * 
     * @param employeeId        员工ID
     * @param basicInfo         员工基本信息
     * @param jobInfo           员工工作信息
     * @param credentialInfo    员工证件信息
     * @param emergencyContacts 紧急联系人
     * @param currentUserId     当前用户ID
     * @return 员工
     */
    Employee modifyEmployee(EmployeeId employeeId, EmployeeBasicInfo basicInfo, EmployeeJobInfo jobInfo,
            EmployeeCredentialInfo credentialInfo, List<EmergencyContact> emergencyContacts,
            UserId currentUserId);

    /**
     * 删除员工
     * 
     * @param employeeId    员工ID
     * @param currentUserId 当前用户ID
     */
    Employee deleteEmployee(EmployeeId employeeId, UserId currentUserId);

    /**
     * 员工入职
     * 
     * @param employeeId    员工ID
     * @param onboardDate   入职日期
     * @param currentUserId 当前用户ID
     * @return 员工
     */
    Employee onboardEmployee(EmployeeId employeeId, LocalDate onboardDate, UserId currentUserId);

    /**
     * 员工离职
     * 
     * @param employeeId    员工ID
     * @param offboardDate  离职日期
     * @param reason        离职原因
     * @param currentUserId 当前用户ID
     * @return 员工
     */
    Employee offboardEmployee(EmployeeId employeeId, LocalDate offboardDate, String reason, UserId currentUserId);
}
