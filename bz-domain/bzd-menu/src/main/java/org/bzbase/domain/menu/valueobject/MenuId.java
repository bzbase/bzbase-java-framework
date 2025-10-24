package org.bzbase.domain.menu.valueobject;

import lombok.EqualsAndHashCode;
import org.bzbase.library.ddd.type.AbstractId;

/**
 * 菜单聚合根唯一标识
 *
 * @author legendjw
 */
@EqualsAndHashCode(callSuper = true)
public class MenuId extends AbstractId {
    public MenuId(String value) {
        super(value);
    }
}