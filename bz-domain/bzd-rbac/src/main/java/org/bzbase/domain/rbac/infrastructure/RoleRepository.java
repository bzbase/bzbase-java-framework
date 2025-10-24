package org.bzbase.domain.rbac.infrastructure;

import org.bzbase.domain.rbac.Role;
import org.bzbase.domain.rbac.valueobject.RoleId;
import org.bzbase.library.ddd.type.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 角色资源库
 */
public interface RoleRepository extends Repository<Role, RoleId> {
	/**
	 * 根据角色ID列表查询角色
	 * 
	 * @param ids            角色ID列表
	 * @return 角色列表
	 */
	List<Role> findByIds(Set<RoleId> ids);

	/**
	 * 根据角色编码查询角色
	 * 
	 * @param code           角色编码
	 * @return 角色
	 */
	Optional<Role> findByCode(String code);

	/**
	 * 检查指定角色编码是否存在
	 * 
	 * @param code           角色编码
	 * @return 是否存在
	 */
	boolean existsByCode(String code);

	/**
	 * 检查指定角色名称是否存在
	 * 
	 * @param name           角色名称
	 * @return 是否存在
	 */
	boolean existsByName(String name);

	/**
	 * 检查是否存在子角色
	 * 
	 * @param parentId       父角色ID
	 * @return 是否存在子角色
	 */
	boolean existsByParentId(RoleId parentId);
}
