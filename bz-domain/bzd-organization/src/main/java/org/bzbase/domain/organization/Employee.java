package org.bzbase.domain.organization;

import java.time.LocalDate;
import java.util.List;

import org.bzbase.domain.organization.valueobject.EmergencyContact;
import org.bzbase.domain.organization.valueobject.EmployeeBasicInfo;
import org.bzbase.domain.organization.valueobject.EmployeeCredentialInfo;
import org.bzbase.domain.organization.valueobject.EmployeeId;
import org.bzbase.domain.organization.valueobject.EmployeeJobInfo;
import org.bzbase.domain.organization.valueobject.EmployeeStatus;
import org.bzbase.domain.organization.valueobject.OrganizationId;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.LifecycleAggregateRoot;
import org.bzbase.primitive.user.UserId;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * 员工聚合根
 */
@Getter
@SuperBuilder(toBuilder = true)
public class Employee extends LifecycleAggregateRoot<EmployeeId> {
    /**
     * 员工ID
     */
    private EmployeeId id;

    /**
     * 所属组织ID
     */
    private OrganizationId organizationId;

    /**
     * 员工基本信息
     */
    private EmployeeBasicInfo basicInfo;

    /**
     * 员工工作信息
     */
    private EmployeeJobInfo jobInfo;

    /**
     * 员工证件信息
     */
    private EmployeeCredentialInfo credentialInfo;

    /**
     * 紧急联系人
     */
    private List<EmergencyContact> emergencyContacts;

    /**
     * 员工状态
     */
    private EmployeeStatus status;

    /**
     * 关联的用户账号ID
     */
    private UserId userAccountId;

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
    public static Employee create(EmployeeId employeeId, OrganizationId organizationId, EmployeeBasicInfo basicInfo,
            EmployeeJobInfo jobInfo, EmployeeCredentialInfo credentialInfo, List<EmergencyContact> emergencyContacts,
            UserId currentUserId) {
        Employee employee = Employee.builder()
                .id(employeeId)
                .organizationId(organizationId)
                .basicInfo(basicInfo)
                .jobInfo(jobInfo)
                .credentialInfo(credentialInfo)
                .emergencyContacts(emergencyContacts)
                .status(EmployeeStatus.ACTIVE)
                .build();
        employee.markAsCreated(currentUserId);
        return employee;
    }

    /**
     * 修改员工信息
     * 
     * @param basicInfo         员工基本信息
     * @param jobInfo           员工工作信息
     * @param credentialInfo    员工证件信息
     * @param emergencyContacts 紧急联系人
     * @param currentUserId     当前用户ID
     */
    public void modify(EmployeeBasicInfo basicInfo, EmployeeJobInfo jobInfo, EmployeeCredentialInfo credentialInfo,
            List<EmergencyContact> emergencyContacts, UserId currentUserId) {
        this.basicInfo = basicInfo;
        this.jobInfo = jobInfo;
        this.credentialInfo = credentialInfo;
        this.emergencyContacts = emergencyContacts;
        this.markAsUpdated(currentUserId);
    }

    /**
     * 删除员工
     * 
     * @param currentUserId 当前用户ID
     */
    public void delete(UserId currentUserId) {
        this.markAsDeleted(currentUserId);
    }

    /**
     * 员工入职
     * 
     * @param onboardDate   入职日期
     * @param currentUserId 当前用户ID
     */
    public void onboard(LocalDate onboardDate, UserId currentUserId) {
        this.jobInfo = this.jobInfo.withOnboardDate(onboardDate)
                              .withOffboardDate(null)
                              .withOffboardReason(null);
        this.status = EmployeeStatus.ACTIVE;
        this.markAsUpdated(currentUserId);
    }

    /**
     * 员工离职
     * 
     * @param offboardDate  离职日期
     * @param reason        离职原因
     * @param currentUserId 当前用户ID
     */
    public void offboard(LocalDate offboardDate, String reason, UserId currentUserId) {
        if (this.status != EmployeeStatus.ACTIVE) {
            throw new DomainException("只有在职员工可以执行离职操作");
        }

        if (offboardDate.isBefore(this.jobInfo.getOnboardDate())) {
            throw new DomainException("离职日期不能早于入职日期");
        }

        this.jobInfo = this.jobInfo.withOffboardDate(offboardDate).withOffboardReason(reason);
        this.status = EmployeeStatus.TERMINATED;
        this.markAsUpdated(currentUserId);
    }

    /**
     * 是否关联了用户账号
     * 
     * @return 是否关联了用户账号
     */
    public boolean hasUserAccount() {
        return this.userAccountId != null;
    }

    /**
     * 绑定用户账号
     * 
     * @param userAccountId 用户账号ID
     */
    public void bindUserAccount(UserId userAccountId) {
        if (this.userAccountId != null) {
            throw new DomainException("员工已绑定用户账号");
        }
        this.userAccountId = userAccountId;
    }

    /**
     * 解绑用户账号
     */
    public void unbindUserAccount() {
        if (this.userAccountId == null) {
            throw new DomainException("员工未绑定用户账号");
        }
        this.userAccountId = null;
    }
}
