package org.bzbase.domain.menu.service;
import org.bzbase.domain.menu.Menu;
import org.bzbase.domain.menu.valueobject.MenuId;

/**
 * 菜单领域服务
 */
public interface MenuManageService {
	/**
	 * 创建菜单
	 * 
	 * @param menu 菜单
	 * @return 菜单
	 */
	Menu createMenu(Menu menu);

	/**
	 * 修改菜单
	 * 
	 * @param menu 菜单
	 * @return 菜单
	 */
	Menu modifyMenu(Menu menu);

	/**
	 * 删除菜单
	 * 
	 * @param menuId 菜单ID
	 * @return 菜单
	 */
	Menu deleteMenu(MenuId menuId);
}
