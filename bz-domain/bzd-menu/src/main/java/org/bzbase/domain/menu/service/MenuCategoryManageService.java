package org.bzbase.domain.menu.service;

import org.bzbase.domain.menu.MenuCategory;
import org.bzbase.domain.menu.valueobject.MenuCategoryId;
import org.bzbase.primitive.user.UserId;

/**
 * 菜单分类领域服务
 */
public interface MenuCategoryManageService {
	/**
	 * 创建菜单分类
	 * 
	 * @param name           菜单分类名称
	 * @param code           菜单分类编码
	 * @param currentUserId  当前用户ID
	 * @return 菜单分类
	 */
	MenuCategory createMenuCategory(String name, String code, UserId currentUserId);

	/**
	 * 修改菜单分类
	 * 
	 * @param menuCategoryId 菜单分类ID
	 * @param name           菜单分类名称
	 * @param code           菜单分类编码
	 * @param currentUserId  当前用户ID
	 * @return 菜单分类
	 */
	MenuCategory modifyMenuCategory(MenuCategoryId menuCategoryId, String name, String code, UserId currentUserId);

	/**
	 * 删除菜单分类
	 * 
	 * @param menuCategoryId 菜单分类ID
	 * @param currentUserId 当前用户ID
	 * @return 菜单分类
	 */
	MenuCategory deleteMenuCategory(MenuCategoryId menuCategoryId, UserId currentUserId);
}
