package org.bzbase.domain.rbac.infrastructure;

import java.util.List;

import org.bzbase.domain.rbac.Permission;
import org.bzbase.domain.rbac.valueobject.PermissionId;
import org.bzbase.library.ddd.type.Repository;

/**
 * 权限资源库
 */
public interface PermissionRepository extends Repository<Permission, PermissionId> {
	/**
	 * 查询全部权限
	 * 
	 * @return 权限列表
	 */
	List<Permission> findAll();

	/**
	 * 根据父权限ID查询权限列表
	 * 
	 * @param parentId 父权限ID
	 * @return 权限列表
	 */
	List<Permission> findByParentId(PermissionId parentId);

	/**
	 * 判断权限编码是否存在
	 * 
	 * @param code 权限编码
	 * @return 是否存在
	 */
	boolean existsByCode(String code);

	/**
	 * 判断权限父ID是否存在
	 * 
	 * @param parentId 权限父ID
	 * @return 是否存在
	 */
	boolean existsByParentId(PermissionId parentId);
}
