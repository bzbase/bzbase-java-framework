package org.bzbase.domain.rbac.service.impl;

import java.util.Optional;
import java.util.Set;

import org.bzbase.domain.rbac.RoleAssignment;
import org.bzbase.domain.rbac.infrastructure.RoleAssignmentRepository;
import org.bzbase.domain.rbac.infrastructure.RoleRepository;
import org.bzbase.domain.rbac.service.RoleAssignService;
import org.bzbase.domain.rbac.valueobject.RoleAssignmentId;
import org.bzbase.domain.rbac.valueobject.RoleId;
import org.bzbase.domain.rbac.valueobject.Subject;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.IdGenerator;
import org.bzbase.primitive.user.UserId;

import lombok.RequiredArgsConstructor;

/**
 * 角色分配服务实现
 */
@RequiredArgsConstructor
public class RoleAssignServiceImpl implements RoleAssignService {
	private final IdGenerator<String> idGenerator;
	private final RoleAssignmentRepository roleAssignmentRepository;
	private final RoleRepository roleRepository;

	@Override
	public RoleAssignment assignRoles(Subject subject, Set<RoleId> roleIds, UserId currentUserId) {
		if (!roleIds.isEmpty()) {
			roleIds.forEach(roleId -> {
				roleRepository.findById(roleId).orElseThrow(() -> new DomainException("指定角色不存在"));
			});
		}

		return roleAssignmentRepository.findBySubject(subject)
				.map(r -> {
					r.assignRoles(roleIds, currentUserId);
					return r;
				})
				.orElseGet(() -> {
					return RoleAssignment.create(
							new RoleAssignmentId(idGenerator.generate()),
							subject,
							roleIds,
							currentUserId);
				});
	}

	@Override
	public RoleAssignment revokeAssignment(Subject subject, UserId currentUserId) {
		Optional<RoleAssignment> roleAssignmentOptional = roleAssignmentRepository.findBySubject(subject);
		if (!roleAssignmentOptional.isPresent()) {
			return null;
		}

		RoleAssignment roleAssignment = roleAssignmentOptional.get();
		roleAssignment.delete(currentUserId);
		return roleAssignment;
	}
}