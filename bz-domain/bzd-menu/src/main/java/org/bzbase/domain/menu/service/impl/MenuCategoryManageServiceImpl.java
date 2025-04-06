package org.bzbase.domain.menu.service.impl;

import org.bzbase.domain.menu.MenuCategory;
import org.bzbase.domain.menu.infrastructure.MenuCategoryRepository;
import org.bzbase.domain.menu.infrastructure.MenuRepository;
import org.bzbase.domain.menu.service.MenuCategoryManageService;
import org.bzbase.domain.menu.valueobject.MenuCategoryId;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.IdGenerator;
import org.bzbase.primitive.user.UserId;

import lombok.RequiredArgsConstructor;

/**
 * 菜单分类领域服务实现
 */
@RequiredArgsConstructor
public class MenuCategoryManageServiceImpl implements MenuCategoryManageService {
	private final IdGenerator<String> idGenerator;
	private final MenuCategoryRepository menuCategoryRepository;
	private final MenuRepository menuRepository;

	@Override
	public MenuCategory createMenuCategory(String name, String code, UserId currentUserId) {
		// 菜单分类编码不能重复
		boolean isMenuCategoryCodeExists = menuCategoryRepository.existsByCode(code);
		if (isMenuCategoryCodeExists) {
			throw new DomainException("菜单分类编码已存在");
		}
		MenuCategoryId id = new MenuCategoryId(idGenerator.generate());
		return MenuCategory.create(id, name, code, currentUserId);
	}

	@Override
	public MenuCategory modifyMenuCategory(MenuCategoryId menuCategoryId, String name, String code,
			UserId currentUserId) {
		MenuCategory menuCategory = menuCategoryRepository.findById(menuCategoryId)
				.orElseThrow(() -> new DomainException("菜单分类不存在"));

		// 菜单分类编码不能重复
		boolean isMenuCategoryCodeChanged = !menuCategory.getCode().equals(code);
		if (isMenuCategoryCodeChanged) {
			boolean isMenuCategoryCodeExists = menuCategoryRepository.existsByCode(code);
			if (isMenuCategoryCodeExists) {
				throw new DomainException("菜单分类编码已存在");
			}
		}

		menuCategory.modify(name, code, currentUserId);
		return menuCategory;
	}

	@Override
	public MenuCategory deleteMenuCategory(MenuCategoryId menuCategoryId, UserId currentUserId) {
		MenuCategory menuCategory = menuCategoryRepository.findById(menuCategoryId)
				.orElseThrow(() -> new DomainException("菜单分类不存在"));

		// 检查是否存在菜单
		boolean hasMenu = menuRepository.existsByCategoryId(menuCategoryId);
		if (hasMenu) {
			throw new DomainException("该菜单分类下存在菜单，无法删除");
		}

		menuCategory.delete(currentUserId);
		return menuCategory;
	}
}