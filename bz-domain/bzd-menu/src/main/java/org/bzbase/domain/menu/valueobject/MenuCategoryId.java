package org.bzbase.domain.menu.valueobject;

import lombok.EqualsAndHashCode;
import org.bzbase.library.ddd.type.AbstractId;

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