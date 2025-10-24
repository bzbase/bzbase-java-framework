package org.bzbase.domain.organization;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bzbase.domain.organization.valueobject.*;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.AbstractAggregateRoot;
import org.bzbase.primitive.organization.OrganizationId;
import org.bzbase.primitive.tenant.TenantId;
import org.bzbase.primitive.user.UserId;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 员工聚合根
 */
@Getter
@Setter
@Builder(toBuilder = true)
public class Employee extends AbstractAggregateRoot<EmployeeId> {
    /**
     * 员工ID
     */
    private EmployeeId id;

    /**
     * 租户ID
     */
	private TenantId tenantId;

    /**
     * 组织ID
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
     * 员工扩展属性
     */
    private Map<String, Object> extendedAttributes;

    /**
     * 关联的用户账号ID
     */
    private UserId userAccountId;

    /**
     * 创建人
     */
	private UserId createdBy;

	/**
	 * 创建时间
	 */
	private Instant createdAt;

    /**
     * 员工入职
     * 
     * @param onboardDate 入职日期
     */
    public void onboard(LocalDate onboardDate) {
        this.jobInfo = this.jobInfo.withOnboardDate(onboardDate)
                              .withOffboardDate(null)
                              .withOffboardReason(null);
        this.status = EmployeeStatus.ACTIVE;
    }

    /**
     * 员工离职
     * 
     * @param offboardDate  离职日期
     * @param reason        离职原因
     */
    public void offboard(LocalDate offboardDate, String reason) {
        if (this.status != EmployeeStatus.ACTIVE) {
            throw new DomainException("只有在职员工可以执行离职操作");
        }

        if (offboardDate.isBefore(this.jobInfo.getOnboardDate())) {
            throw new DomainException("离职日期不能早于入职日期");
        }

        this.jobInfo = this.jobInfo.withOffboardDate(offboardDate).withOffboardReason(reason);
        this.status = EmployeeStatus.TERMINATED;
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
