package org.bzbase.domain.rbac.specification;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bzbase.domain.rbac.Role;
import org.bzbase.domain.rbac.infrastructure.RoleRepository;
import org.bzbase.library.ddd.specification.AbstractSpecification;

/**
 * 角色编码唯一性规约
 * 
 * @author legendjw
 */
@Getter
@RequiredArgsConstructor
public class RoleCodeUniqueSpec extends AbstractSpecification<Role> {
    private final RoleRepository roleRepository;
    
    @Override
    public boolean isSatisfiedBy(Role role) {
        // 检查编码是否已存在
        return !roleRepository.existsByCode(role.getCode());
    }
}
