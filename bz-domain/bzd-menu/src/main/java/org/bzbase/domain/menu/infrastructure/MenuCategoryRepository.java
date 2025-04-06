package org.bzbase.domain.menu.infrastructure;

import java.util.Optional;

import org.bzbase.domain.menu.MenuCategory;
import org.bzbase.domain.menu.valueobject.MenuCategoryId;
import org.bzbase.library.ddd.type.Repository;

/**
 * 菜单分类资源库
 */
public interface MenuCategoryRepository extends Repository<MenuCategory, MenuCategoryId> {
    /**
     * 根据编码查询菜单分类
     * 
     * @param code 菜单分类编码
     * @return 菜单分类
     */
    Optional<MenuCategory> findByCode(String code);

    /**
     * 检查指定分类下菜单名称是否存在
     * 
     * @param code 菜单分类编码
     * @return 是否存在
     */
    boolean existsByCode(String code);
}