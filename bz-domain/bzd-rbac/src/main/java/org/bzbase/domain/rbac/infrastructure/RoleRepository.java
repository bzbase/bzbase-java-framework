package org.bzbase.domain.rbac.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.bzbase.domain.rbac.Role;
import org.bzbase.domain.rbac.valueobject.RoleId;
import org.bzbase.library.ddd.type.Repository;
import org.bzbase.primitive.organization.OrganizationId;
import org.bzbase.primitive.tenant.TenantId;

/**
 * 角色资源库
 */
public interface RoleRepository extends Repository<Role, RoleId> {
	/**
	 * 根据角色ID查询角色
	 * 
	 * @param tenantId       租户ID
	 * @param organizationId 组织ID
	 * @param id             角色ID
	 * @return 角色
	 */
	Optional<Role> findById(TenantId tenantId, OrganizationId organizationId, RoleId id);

	/**
	 * 根据角色ID列表查询角色
	 * 
	 * @param tenantId       租户ID
	 * @param organizationId 组织ID
	 * @param ids            角色ID列表
	 * @return 角色列表
	 */
	List<Role> findByIds(TenantId tenantId, OrganizationId organizationId, Set<RoleId> ids);

	/**
	 * 根据角色编码查询角色
	 * 
	 * @param tenantId       租户ID
	 * @param organizationId 组织ID
	 * @param code           角色编码
	 * @return 角色
	 */
	Optional<Role> findByCode(TenantId tenantId, OrganizationId organizationId, String code);

	/**
	 * 检查指定角色编码是否存在
	 * 
	 * @param tenantId       租户ID
	 * @param organizationId 组织ID
	 * @param code           角色编码
	 * @return 是否存在
	 */
	boolean existsByCode(TenantId tenantId, OrganizationId organizationId, String code);

	/**
	 * 检查指定角色名称是否存在
	 * 
	 * @param tenantId       租户ID
	 * @param organizationId 组织ID
	 * @param name           角色名称
	 * @return 是否存在
	 */
	boolean existsByName(TenantId tenantId, OrganizationId organizationId, String name);

	/**
	 * 检查是否存在子角色
	 * 
	 * @param tenantId       租户ID
	 * @param organizationId 组织ID
	 * @param parentId       父角色ID
	 * @return 是否存在子角色
	 */
	boolean existsByParentId(TenantId tenantId, OrganizationId organizationId, RoleId parentId);
}
