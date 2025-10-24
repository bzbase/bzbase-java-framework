package org.bzbase.domain.rbac.specification;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bzbase.domain.rbac.Role;
import org.bzbase.domain.rbac.infrastructure.RoleRepository;
import org.bzbase.library.ddd.specification.AbstractSpecification;

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
        return !roleRepository.existsByParentId(role.getId());
    }
}
