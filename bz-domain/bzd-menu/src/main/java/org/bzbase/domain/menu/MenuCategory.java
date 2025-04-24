package org.bzbase.domain.menu;

import java.time.Instant;

import org.bzbase.domain.menu.valueobject.MenuCategoryId;
import org.bzbase.library.ddd.type.AbstractAggregateRoot;
import org.bzbase.primitive.user.UserId;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder(toBuilder = true)
@Setter
public class MenuCategory extends AbstractAggregateRoot<MenuCategoryId> {
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
	 * 创建人
	 */
	private UserId createdBy;

	/**
	 * 创建时间
	 */
	private Instant createdAt;
}