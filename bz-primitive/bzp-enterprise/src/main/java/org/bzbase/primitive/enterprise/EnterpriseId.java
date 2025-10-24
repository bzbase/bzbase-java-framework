package org.bzbase.primitive.enterprise;

import lombok.EqualsAndHashCode;
import org.bzbase.library.ddd.type.AbstractId;

/**
 * 企业聚合根唯一标识
 */
@EqualsAndHashCode(callSuper = true)
public class EnterpriseId extends AbstractId {
    /**
     * 创建企业标识
     *
     * @param value 标识字符串
     */
    public EnterpriseId(String value) {
        super(value);
    }
}
