package org.bzbase.domain.menu.service.impl;

import org.bzbase.domain.menu.Menu;
import org.bzbase.domain.menu.infrastructure.MenuRepository;
import org.bzbase.domain.menu.service.MenuManageService;
import org.bzbase.domain.menu.valueobject.MenuId;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.IdGenerator;
import org.bzbase.primitive.user.UserId;

import lombok.RequiredArgsConstructor;

/**
 * 菜单领域服务实现
 */
@RequiredArgsConstructor
public class MenuManageServiceImpl implements MenuManageService {
	private final IdGenerator<String> idGenerator;
	private final MenuRepository menuRepository;

	@Override
	public Menu createMenu(Menu.MenuBuilder menuBuilder, UserId currentUserId) {
		menuBuilder.id(new MenuId(idGenerator.generate()));
		Menu menu = Menu.create(menuBuilder, currentUserId);
		// 同一分类下菜单名称不能重复
		boolean isMenuNameExistsInCategory = menuRepository.existsByCategoryIdAndName(menu.getCategoryId(),
				menu.getName());
		if (isMenuNameExistsInCategory) {
			throw new DomainException("指定分类下菜单名称已存在");
		}

		return menu;
	}

	@Override
	public Menu modifyMenu(MenuId menuId, Menu.MenuBuilder menuBuilder, UserId currentUserId) {
		Menu menu = menuRepository.findById(menuId).orElseThrow(() -> new DomainException("菜单不存在"));
		Menu newMenu = menuBuilder.build();

		// 同一分类下菜单名称不能重复
		boolean isMenuNameChanged = !menu.getName().equals(newMenu.getName());
		if (isMenuNameChanged) {
			boolean isMenuNameExistsInCategory = menuRepository.existsByCategoryIdAndName(newMenu.getCategoryId(),
					newMenu.getName());
			if (isMenuNameExistsInCategory) {
				throw new DomainException("指定分类下菜单名称已存在");
			}
		}
		menu.modify(menuBuilder, currentUserId);
		return menu;
	}

	@Override
	public Menu deleteMenu(MenuId menuId, UserId currentUserId) {
		Menu menu = menuRepository.findById(menuId).orElseThrow(() -> new DomainException("菜单不存在"));

		// 检查是否存在子菜单
		boolean hasChildren = menuRepository.existsByParentId(menuId);
		if (hasChildren) {
			throw new DomainException("该菜单下存在子菜单，无法删除");
		}

		menu.markAsDeleted(currentUserId.getValue());
		return menu;
	}
}
