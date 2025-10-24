package org.bzbase.domain.rbac.infrastructure;

import org.bzbase.domain.rbac.RoleAssignment;
import org.bzbase.domain.rbac.valueobject.RoleAssignmentId;
import org.bzbase.domain.rbac.valueobject.Subject;
import org.bzbase.library.ddd.type.Repository;

import java.util.Optional;

/**
 * 角色分配资源库
 * 
 * @author legendjw
 */
public interface RoleAssignmentRepository extends Repository<RoleAssignment, RoleAssignmentId> {
	/**
	 * 根据主体查询角色分配
	 *
	 * @param subject        主体
	 * @return 角色分配
	 */
	Optional<RoleAssignment> findBySubject(Subject subject);
}
