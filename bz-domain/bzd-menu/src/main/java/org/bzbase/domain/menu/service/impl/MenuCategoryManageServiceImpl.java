package org.bzbase.domain.menu.service.impl;

import lombok.RequiredArgsConstructor;
import org.bzbase.domain.menu.MenuCategory;
import org.bzbase.domain.menu.infrastructure.MenuCategoryRepository;
import org.bzbase.domain.menu.infrastructure.MenuRepository;
import org.bzbase.domain.menu.service.MenuCategoryManageService;
import org.bzbase.domain.menu.valueobject.MenuCategoryId;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.IdGenerator;

import java.time.Instant;

/**
 * 菜单分类领域服务实现
 */
@RequiredArgsConstructor
public class MenuCategoryManageServiceImpl implements MenuCategoryManageService {
	private final IdGenerator<String> idGenerator;
	private final MenuCategoryRepository menuCategoryRepository;
	private final MenuRepository menuRepository;

	@Override
	public MenuCategory createMenuCategory(MenuCategory menuCategory) {
		if (menuCategory.getId() == null) {
			menuCategory.setId(new MenuCategoryId(idGenerator.generate()));
		}
		menuCategory.setCreatedAt(Instant.now());
		// 菜单分类编码不能重复
		boolean isMenuCategoryCodeExists = menuCategoryRepository.existsByCode(menuCategory.getCode());
		if (isMenuCategoryCodeExists) {
			throw new DomainException("菜单分类编码已存在");
		}
		return menuCategory;
	}

	@Override
	public MenuCategory modifyMenuCategory(MenuCategory menuCategory) {
		MenuCategory oldMenuCategory = getMenuCategoryById(menuCategory.getId());

		// 菜单分类编码不能重复
		boolean isMenuCategoryCodeChanged = !menuCategory.getCode().equals(oldMenuCategory.getCode());
		if (isMenuCategoryCodeChanged) {
			boolean isMenuCategoryCodeExists = menuCategoryRepository.existsByCode(menuCategory.getCode());
			if (isMenuCategoryCodeExists) {
				throw new DomainException("菜单分类编码已存在");
			}
		}

		return menuCategory;
	}

	@Override
	public MenuCategory deleteMenuCategory(MenuCategoryId menuCategoryId) {
		MenuCategory menuCategory = getMenuCategoryById(menuCategoryId);

		// 检查是否存在菜单
		boolean hasMenu = menuRepository.existsByCategoryId(menuCategoryId);
		if (hasMenu) {
			throw new DomainException("该菜单分类下存在菜单，无法删除");
		}

		return menuCategory;
	}

	private MenuCategory getMenuCategoryById(MenuCategoryId menuCategoryId) {
		return menuCategoryRepository.findById(menuCategoryId).orElseThrow(() -> new DomainException("菜单分类不存在"));
	}
}