package org.bzbase.domain.rbac.service.impl;

import org.bzbase.domain.rbac.Permission;
import org.bzbase.domain.rbac.infrastructure.PermissionRepository;
import org.bzbase.domain.rbac.service.PermissionManageService;
import org.bzbase.domain.rbac.valueobject.PermissionId;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.IdGenerator;
import org.bzbase.primitive.user.UserId;

import lombok.RequiredArgsConstructor;

/**
 * 权限管理服务实现
 */
@RequiredArgsConstructor
public class PermissionManageServiceImpl implements PermissionManageService {
	private final IdGenerator<String> idGenerator;
	private final PermissionRepository permissionRepository;

	@Override
	public Permission createPermission(PermissionId parentId, String code, String name, UserId currentUserId) {
		if (permissionRepository.existsByCode(code)) {
			throw new DomainException(String.format("权限编码：%s 已存在", code));
		}
		PermissionId id = new PermissionId(idGenerator.generate());
		return Permission.create(id, parentId, code, name, currentUserId);
	}

	@Override
	public Permission modifyPermission(PermissionId id, PermissionId parentId, String code, String name,
			UserId currentUserId) {
		boolean isCodeChanged = !permissionRepository.existsByCode(code);
		if (isCodeChanged && permissionRepository.existsByCode(code)) {
			throw new DomainException(String.format("权限编码：%s 已存在", code));
		}
		Permission permission = permissionRepository.findById(id).orElseThrow(() -> new DomainException("权限不存在"));
		permission.modify(parentId, code, name, currentUserId);
		return permission;
	}

	@Override
	public Permission deletePermission(PermissionId id, UserId currentUserId) {
		Permission permission = permissionRepository.findById(id).orElseThrow(() -> new DomainException("权限不存在"));
		if (permissionRepository.existsByParentId(id)) {
			throw new DomainException("权限存在子权限，无法删除");
		}
		permission.delete(currentUserId);
		return permission;
	}
}