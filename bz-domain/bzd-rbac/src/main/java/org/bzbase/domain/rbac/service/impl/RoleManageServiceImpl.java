package org.bzbase.domain.rbac.service.impl;

import java.util.Set;

import org.bzbase.domain.rbac.Role;
import org.bzbase.domain.rbac.infrastructure.RoleRepository;
import org.bzbase.domain.rbac.service.RoleManageService;
import org.bzbase.domain.rbac.valueobject.RoleId;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.IdGenerator;
import org.bzbase.primitive.user.UserId;

import lombok.RequiredArgsConstructor;

/**
 * 角色管理服务实现
 */
@RequiredArgsConstructor
public class RoleManageServiceImpl implements RoleManageService {
	private final IdGenerator<String> idGenerator;
	private final RoleRepository roleRepository;

	@Override
	public Role createRole(RoleId parentId, String code, String name, String description, UserId currentUserId) {
		if (roleRepository.existsByCode(code)) {
			throw new DomainException("角色编码已存在");
		}
		if (roleRepository.existsByName(name)) {
			throw new DomainException("角色名称已存在");
		}
		RoleId id = new RoleId(idGenerator.generate());
		return Role.create(id, parentId, code, name, description, currentUserId);
	}

	@Override
	public Role modifyRole(RoleId id, RoleId parentId, String code, String name, String description,
			UserId currentUserId) {
		Role role = roleRepository.findById(id).orElseThrow(() -> new DomainException("角色不存在"));
		boolean isCodeChanged = !role.getCode().equals(code);
		if (isCodeChanged && roleRepository.existsByCode(code)) {
			throw new DomainException("角色编码已存在");
		}
		boolean isNameChanged = !role.getName().equals(name);
		if (isNameChanged && roleRepository.existsByName(name)) {
			throw new DomainException("角色名称已存在");
		}
		role.modify(parentId, code, name, description, currentUserId);
		return role;
	}

	@Override
	public Role deleteRole(RoleId id, UserId currentUserId) {
		Role role = roleRepository.findById(id).orElseThrow(() -> new DomainException("角色不存在"));
		role.delete(currentUserId);
		return role;
	}

	@Override
	public Role assignPermissions(RoleId id, Set<String> permissions, UserId currentUserId) {
		Role role = roleRepository.findById(id).orElseThrow(() -> new DomainException("角色不存在"));
		role.assignPermissions(permissions, currentUserId);
		return role;
	}
}
