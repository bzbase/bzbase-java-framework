package org.bzbase.domain.rbac.infrastructure;

import java.util.Optional;

import org.bzbase.domain.rbac.RoleAssignment;
import org.bzbase.domain.rbac.valueobject.RoleAssignmentId;
import org.bzbase.domain.rbac.valueobject.Subject;
import org.bzbase.library.ddd.type.Repository;
import org.bzbase.primitive.organization.OrganizationId;
import org.bzbase.primitive.tenant.TenantId;

/**
 * 角色分配资源库
 * 
 * @author legendjw
 */
public interface RoleAssignmentRepository extends Repository<RoleAssignment, RoleAssignmentId> {
	/**
	 * 根据主体查询角色分配
	 *
	 * @param tenantId       租户ID
	 * @param organizationId 组织ID
	 * @param subject        主体
	 * @return 角色分配
	 */
	Optional<RoleAssignment> findBySubject(TenantId tenantId, OrganizationId organizationId, Subject subject);
}