package org.bzbase.domain.menu;

import org.bzbase.domain.menu.valueobject.MenuCategoryId;
import org.bzbase.library.ddd.type.LifecycleAggregateRoot;
import org.bzbase.primitive.user.UserId;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
public class MenuCategory extends LifecycleAggregateRoot<MenuCategoryId> {
	/**
	 * 菜单分类ID
	 */
	private MenuCategoryId id;

	/**
	 * 菜单分类名称
	 */
	private String name;

	/**
	 * 菜单分类编码
	 */
	private String code;

	/**
	 * 创建菜单分类
	 * 
	 * @param id        菜单分类ID
	 * @param name      菜单分类名称
	 * @param code      菜单分类编码
	 * @param currentUserId 当前用户ID
	 * @return 菜单分类
	 */
	public static MenuCategory create(MenuCategoryId id, String name, String code, UserId currentUserId) {
		MenuCategory menuCategory = MenuCategory.builder()
				.id(id)
				.name(name)
				.code(code)
				.build();
		menuCategory.markAsCreated(currentUserId);
		return menuCategory;
	}

	/**
	 * 修改菜单分类
	 * 
	 * @param name          菜单分类名称
	 * @param code          菜单分类编码
	 * @param currentUserId 当前用户ID
	 */
	public void modify(String name, String code, UserId currentUserId) {
		this.name = name;
		this.code = code;
		this.markAsUpdated(currentUserId);
	}

	/**
	 * 删除菜单分类
	 * 
	 * @param currentUserId 当前用户ID
	 */
	public void delete(UserId currentUserId) {
		this.markAsDeleted(currentUserId);
	}
}
