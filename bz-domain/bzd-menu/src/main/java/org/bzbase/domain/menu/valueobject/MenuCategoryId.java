package org.bzbase.domain.menu.valueobject;

import org.bzbase.library.ddd.type.AbstractId;

import lombok.EqualsAndHashCode;

/**
 * 菜单分类唯一标识
 *
 * @author legendjw
 */
@EqualsAndHashCode(callSuper = true)
public class MenuCategoryId extends AbstractId {
    public MenuCategoryId(String value) {
        super(value);
    }
}