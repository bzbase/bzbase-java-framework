package org.bzbase.domain.rbac.specification;

import org.bzbase.domain.rbac.Role;
import org.bzbase.domain.rbac.infrastructure.RoleRepository;
import org.bzbase.library.ddd.specification.AbstractSpecification;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 角色名称唯一性规约
 */
@Getter
@RequiredArgsConstructor
public class RoleNameUniqueSpec extends AbstractSpecification<Role> {
    private final RoleRepository roleRepository;
    
    @Override
    public boolean isSatisfiedBy(Role role) {
        // 检查名称是否已存在
        return !roleRepository.existsByName(role.getTenantId(), role.getOrganizationId(), role.getName());
    }
} 