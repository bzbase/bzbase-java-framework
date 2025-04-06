package org.bzbase.domain.menu.infrastructure;

import java.util.List;
import java.util.Set;

import org.bzbase.domain.menu.Menu;
import org.bzbase.domain.menu.valueobject.MenuCategoryId;
import org.bzbase.domain.menu.valueobject.MenuId;
import org.bzbase.library.ddd.type.Repository;

/**
 * 菜单资源库
 */
public interface MenuRepository extends Repository<Menu, MenuId> {
	/**
	 * 根据分类ID查询菜单列表
	 * 
	 * @param categoryId 分类ID
	 * @return 菜单列表
	 */
	List<Menu> findByCategoryId(MenuCategoryId categoryId);

	/**
	 * 根据分类ID和权限列表查询菜单列表,包含父级菜单
	 * 
	 * @param categoryId 分类ID
	 * @param permissions 权限列表
	 * @return 菜单列表
	 */
	List<Menu> findByCategoryIdAndPermissionsIncludeParent(MenuCategoryId categoryId, Set<String> permissions);

	/**
	 * 检查指定分类下是否存在菜单
	 * 
	 * @param categoryId 分类ID
	 * @return 是否存在菜单
	 */
	boolean existsByCategoryId(MenuCategoryId categoryId);

	/**
	 * 检查指定分类下菜单名称是否存在
	 * 
	 * @param categoryId 分类ID
	 * @param name       菜单名称
	 * @return 是否存在
	 */
	boolean existsByCategoryIdAndName(MenuCategoryId categoryId, String name);

	/**
	 * 检查是否存在子菜单
	 * 
	 * @param parentId 父菜单ID
	 * @return 是否存在子菜单
	 */
	boolean existsByParentId(MenuId parentId);
}