package org.bzbase.domain.menu;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bzbase.domain.menu.valueobject.MenuCategoryId;
import org.bzbase.domain.menu.valueobject.MenuId;
import org.bzbase.library.ddd.type.AbstractAggregateRoot;
import org.bzbase.primitive.user.UserId;

import java.time.Instant;
import java.util.Set;

@Getter
@Builder(toBuilder = true)
@Setter
public class Menu extends AbstractAggregateRoot<MenuId> {
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
	private boolean visible;

	/**
	 * 创建人
	 */
	private UserId createdBy;

	/**
	 * 创建时间
	 */
	private Instant createdAt;
}