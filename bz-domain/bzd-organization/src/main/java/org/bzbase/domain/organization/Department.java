package org.bzbase.domain.organization;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bzbase.domain.organization.valueobject.DepartmentId;
import org.bzbase.domain.organization.valueobject.EmployeeId;
import org.bzbase.library.ddd.type.AbstractAggregateRoot;
import org.bzbase.primitive.organization.OrganizationId;
import org.bzbase.primitive.tenant.TenantId;
import org.bzbase.primitive.user.UserId;

import java.time.Instant;

/**
 * 部门聚合根
 */
@Getter
@Setter
@Builder(toBuilder = true)
public class Department extends AbstractAggregateRoot<DepartmentId> {
    /**
     * 部门ID
     */
    private DepartmentId id;

    /**
     * 租户ID
     */
	private TenantId tenantId;

    /**
     * 组织ID
     */
    private OrganizationId organizationId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 上级部门ID
     */
    private DepartmentId parentId;

    /**
     * 部门主管ID
     */
    private EmployeeId managerId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建人
     */
	private UserId createdBy;

	/**
	 * 创建时间
	 */
	private Instant createdAt;
}