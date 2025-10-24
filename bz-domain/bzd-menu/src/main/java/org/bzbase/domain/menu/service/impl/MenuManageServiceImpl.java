package org.bzbase.domain.menu.service.impl;

import lombok.RequiredArgsConstructor;
import org.bzbase.domain.menu.Menu;
import org.bzbase.domain.menu.infrastructure.MenuRepository;
import org.bzbase.domain.menu.service.MenuManageService;
import org.bzbase.domain.menu.valueobject.MenuId;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.IdGenerator;

import java.time.Instant;

/**
 * 菜单领域服务实现
 */
@RequiredArgsConstructor
public class MenuManageServiceImpl implements MenuManageService {
	private final IdGenerator<String> idGenerator;
	private final MenuRepository menuRepository;

	@Override
	public Menu createMenu(Menu menu) {
		if (menu.getId() == null) {
			menu.setId(new MenuId(idGenerator.generate()));
		}
		menu.setCreatedAt(Instant.now());

		// 同一分类下菜单名称不能重复
		boolean isMenuNameExistsInCategory = menuRepository.existsByCategoryIdAndName(menu.getCategoryId(),
				menu.getName());
		if (isMenuNameExistsInCategory) {
			throw new DomainException("指定分类下菜单名称已存在");
		}

		return menu;
	}

	@Override
	public Menu modifyMenu(Menu menu) {
		Menu oldMenu = getMenuById(menu.getId());

		// 同一分类下菜单名称不能重复
		boolean isMenuNameChanged = !menu.getName().equals(oldMenu.getName());
		if (isMenuNameChanged) {
			boolean isMenuNameExistsInCategory = menuRepository.existsByCategoryIdAndName(menu.getCategoryId(),
					menu.getName());
			if (isMenuNameExistsInCategory) {
				throw new DomainException("指定分类下菜单名称已存在");
			}
		}
		return menu;
	}

	@Override
	public Menu deleteMenu(MenuId menuId) {
		Menu menu = getMenuById(menuId);

		// 检查是否存在子菜单
		boolean hasChildren = menuRepository.existsByParentId(menuId);
		if (hasChildren) {
			throw new DomainException("该菜单下存在子菜单，无法删除");
		}
		return menu;
	}

	private Menu getMenuById(MenuId menuId) {
		return menuRepository.findById(menuId).orElseThrow(() -> new DomainException("菜单不存在"));
	}
}
