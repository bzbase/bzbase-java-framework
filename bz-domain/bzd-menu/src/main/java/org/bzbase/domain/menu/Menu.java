package org.bzbase.domain.menu;

import java.util.Set;

import org.bzbase.domain.menu.valueobject.MenuCategoryId;
import org.bzbase.domain.menu.valueobject.MenuId;
import org.bzbase.library.ddd.type.LifecycleAggregateRoot;
import org.bzbase.primitive.user.UserId;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
public class Menu extends LifecycleAggregateRoot<MenuId> {
	/**
	 * 菜单ID
	 */
	private MenuId id;

	/**
	 * 菜单分类
	 */
	private MenuCategoryId categoryId;

	/**
	 * 菜单编码
	 */
	private String code;

	/**
	 * 菜单名称
	 */
	private String name;

	/**
	 * 父级菜单ID
	 */
	private MenuId parentId;

	/**
	 * 菜单路径
	 */
	private String path;

	/**
	 * 菜单图标
	 */
	private String icon;

	/**
	 * 权限标识集合
	 */
	private Set<String> permissions;

	/**
	 * 排序号
	 */
	private Integer sort;

	/**
	 * 是否可见
	 */
	private Boolean visible;

	/**
	 * 创建菜单
	 * 
	 * @param menuBuilder 菜单构建器
	 * @param currentUserId 当前用户ID
	 * @return 菜单
	 */
	public static Menu create(Menu.MenuBuilder menuBuilder, UserId currentUserId) {
		Menu menu = menuBuilder.build();
		menu.markAsCreated(currentUserId);
		return menu;
	}

	/**
	 * 修改菜单
	 * 
	 * @param menuBuilder 菜单构建器
	 * @param currentUserId 当前用户ID
	 * @return 菜单
	 */
	public void modify(Menu.MenuBuilder menuBuilder, UserId currentUserId) {
		this.categoryId = menuBuilder.categoryId;
		this.name = menuBuilder.name;
		this.parentId = menuBuilder.parentId;
		this.path = menuBuilder.path;
		this.icon = menuBuilder.icon;
		this.permissions = menuBuilder.permissions;
		this.sort = menuBuilder.sort;
		this.visible = menuBuilder.visible;
		this.markAsUpdated(currentUserId);
	}

	/**
	 * 删除菜单
	 * 
	 * @param currentUserId 当前用户ID
	 */
	public void delete(UserId currentUserId) {
		this.markAsDeleted(currentUserId);
	}
}
