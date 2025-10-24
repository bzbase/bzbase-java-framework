package org.bzbase.domain.rbac.service.impl;

import lombok.RequiredArgsConstructor;
import org.bzbase.domain.rbac.Permission;
import org.bzbase.domain.rbac.infrastructure.PermissionRepository;
import org.bzbase.domain.rbac.service.PermissionManageService;
import org.bzbase.domain.rbac.specification.PermissionCodeUniqueSpec;
import org.bzbase.domain.rbac.specification.PermissionWithoutChildrenSpec;
import org.bzbase.domain.rbac.valueobject.PermissionId;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.IdGenerator;

import java.time.Instant;

/**
 * 权限管理服务实现
 */
@RequiredArgsConstructor
public class PermissionManageServiceImpl implements PermissionManageService {
	private final IdGenerator<String> idGenerator;
	private final PermissionRepository permissionRepository;

	@Override
	public Permission createPermission(Permission permission) {
		if (permission.getId() == null) {
			permission.setId(new PermissionId(idGenerator.generate()));
		}
		permission.setCreatedAt(Instant.now());

		if (!new PermissionCodeUniqueSpec(permissionRepository).isSatisfiedBy(permission)) {
			throw new DomainException(String.format("权限编码：%s 已存在", permission.getCode()));
		}

		return permission;
	}

	@Override
	public Permission modifyPermission(Permission permission) {
		Permission oldPermission = getPermissionById(permission.getId());

		boolean isCodeChanged = !oldPermission.getCode().equals(permission.getCode());
		if (isCodeChanged && !new PermissionCodeUniqueSpec(permissionRepository).isSatisfiedBy(permission)) {
			throw new DomainException(String.format("权限编码：%s 已存在", permission.getCode()));
		}

		return permission;
	}

	@Override
	public Permission deletePermission(PermissionId id) {
		Permission permission = getPermissionById(id);

		if (!new PermissionWithoutChildrenSpec(permissionRepository).isSatisfiedBy(permission)) {
			throw new DomainException("权限存在子权限，无法删除");
		}

		return permission;
	}

	private Permission getPermissionById(PermissionId id) {
		return permissionRepository.findById(id).orElseThrow(() -> new DomainException("权限不存在"));
	}
}