package org.bzbase.domain.rbac.specification;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bzbase.domain.rbac.Permission;
import org.bzbase.domain.rbac.infrastructure.PermissionRepository;
import org.bzbase.library.ddd.specification.AbstractSpecification;

/**
 * 权限编码唯一性规约
 */
@Getter
@RequiredArgsConstructor
public class PermissionCodeUniqueSpec extends AbstractSpecification<Permission> {
    private final PermissionRepository permissionRepository;
    
    @Override
    public boolean isSatisfiedBy(Permission permission) {
        return !permissionRepository.existsByCode(permission.getCode());
    }
} 