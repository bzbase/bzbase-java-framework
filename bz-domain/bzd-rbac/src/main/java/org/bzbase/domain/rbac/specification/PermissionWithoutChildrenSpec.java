package org.bzbase.domain.rbac.specification;

import org.bzbase.domain.rbac.Permission;
import org.bzbase.domain.rbac.infrastructure.PermissionRepository;
import org.bzbase.library.ddd.specification.AbstractSpecification;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 权限无子权限规约
 */
@Getter
@RequiredArgsConstructor
public class PermissionWithoutChildrenSpec extends AbstractSpecification<Permission> {
    private final PermissionRepository permissionRepository;
    
    @Override
    public boolean isSatisfiedBy(Permission permission) {
        return !permissionRepository.existsByParentId(permission.getId());
    }
} 