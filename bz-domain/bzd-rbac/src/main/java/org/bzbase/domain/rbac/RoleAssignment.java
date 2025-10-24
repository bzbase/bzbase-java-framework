package org.bzbase.domain.rbac;

import lombok.Builder;
import lombok.Getter;
import org.bzbase.domain.rbac.valueobject.RoleAssignmentId;
import org.bzbase.domain.rbac.valueobject.RoleId;
import org.bzbase.domain.rbac.valueobject.Subject;
import org.bzbase.library.ddd.type.AbstractAggregateRoot;
import org.bzbase.primitive.organization.OrganizationId;
import org.bzbase.primitive.tenant.TenantId;
import org.bzbase.primitive.user.UserId;

import java.time.Instant;
import java.util.Collections;
import java.util.Set;

/**
 * 角色分配
 * 
 * @author legendjw
 */
@Getter
@Builder(toBuilder = true)
public class RoleAssignment extends AbstractAggregateRoot<RoleAssignmentId> {
	/**
	 * 角色分配ID
	 */
	private RoleAssignmentId id;

	/**
	 * 租户ID
	 */
	private TenantId tenantId;

	/**
	 * 组织ID
	 */
	private OrganizationId organizationId;

	/**
	 * 分配的主体
	 */
	private Subject subject;

	/**
	 * 分配的角色ID
	 */
	private Set<RoleId> roleIds;

	/**
	 * 操作人
	 */
	private UserId operatedBy;

	/**
	 * 操作时间
	 */
	private Instant operatedAt;

	/**
	 * 分配角色
	 *
	 * @param roleIds 角色ID集合
	 * @param operatedBy 操作人
	 */
	public void assignRoles(Set<RoleId> roleIds, UserId operatedBy) {
		this.roleIds = roleIds;
		this.operatedBy = operatedBy;
		this.operatedAt = Instant.now();
	}

	/**
	 * 撤销角色分配
	 *
	 * @param operatedBy 操作人
	 */
	public void revokeAssignment(UserId operatedBy) {
		this.roleIds = Collections.emptySet();
		this.operatedBy = operatedBy;
		this.operatedAt = Instant.now();
	}
}
