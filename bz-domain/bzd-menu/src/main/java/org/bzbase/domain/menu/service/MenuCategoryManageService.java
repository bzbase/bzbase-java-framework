package org.bzbase.domain.menu.service;

import org.bzbase.domain.menu.MenuCategory;
import org.bzbase.domain.menu.valueobject.MenuCategoryId;

/**
 * 菜单分类领域服务
 */
public interface MenuCategoryManageService {
	/**
	 * 创建菜单分类
	 * 
	 * @param menuCategory 菜单分类
	 * @return 菜单分类
	 */
	MenuCategory createMenuCategory(MenuCategory menuCategory);

	/**
	 * 修改菜单分类
	 * 
	 * @param menuCategory 菜单分类
	 * @return 菜单分类
	 */
	MenuCategory modifyMenuCategory(MenuCategory menuCategory);

	/**
	 * 删除菜单分类
	 * 
	 * @param menuCategoryId 菜单分类ID
	 * @return 菜单分类
	 */
	MenuCategory deleteMenuCategory(MenuCategoryId menuCategoryId);
}
