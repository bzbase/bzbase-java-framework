package org.bzbase.domain.rbac.specification;

import org.bzbase.domain.rbac.Role;
import org.bzbase.domain.rbac.infrastructure.RoleRepository;
import org.bzbase.library.ddd.specification.AbstractSpecification;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 角色无子角色规约
 */
@Getter
@RequiredArgsConstructor
public class RoleWithoutChildrenSpec extends AbstractSpecification<Role> {
    private final RoleRepository roleRepository;
    
    @Override
    public boolean isSatisfiedBy(Role role) {
        // 检查是否不存在子角色
        return !roleRepository.existsByParentId(role.getTenantId(), role.getOrganizationId(), role.getId());
    }
} 