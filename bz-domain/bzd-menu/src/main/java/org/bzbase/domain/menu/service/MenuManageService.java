package org.bzbase.domain.menu.service;
import org.bzbase.domain.menu.Menu;
import org.bzbase.domain.menu.valueobject.MenuId;
import org.bzbase.primitive.user.UserId;

/**
 * 菜单领域服务
 */
public interface MenuManageService {
	/**
	 * 创建菜单
	 * 
	 * @param menuBuilder 菜单构建器
	 * @param currentUserId 当前用户ID
	 * @return 菜单
	 */
	Menu createMenu(Menu.MenuBuilder menuBuilder, UserId currentUserId);

	/**
	 * 修改菜单
	 * 
	 * @param menuBuilder 菜单构建器
	 * @param currentUserId 当前用户ID
	 * @return 菜单
	 */
	Menu modifyMenu(MenuId menuId, Menu.MenuBuilder menuBuilder, UserId currentUserId);

	/**
	 * 删除菜单
	 * 
	 * @param menuId        菜单ID
	 * @param currentUserId 当前用户ID
	 * @return 菜单
	 */
	Menu deleteMenu(MenuId menuId, UserId currentUserId);
}
