package org.bzbase.primitive.enterprise;

import lombok.NonNull;
import lombok.Value;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.ValueObject;

/**
 * 企业名称值对象
 */
@Value
public class EnterpriseName implements ValueObject {
    /**
     * 企业全称
     */
    String name;

    /**
     * 使用预处理后的企业名称创建值对象
     *
     * @param name 企业名称
     */
    private EnterpriseName(String name) {
        this.name = name;
    }

    /**
     * 创建企业名称值对象，自动进行空白处理
     *
     * @param name 企业名称
     * @return 企业名称值对象
     */
    public static EnterpriseName of(@NonNull String name) {
        String normalized = name.trim();
        if (normalized.isEmpty()) {
            throw new DomainException("企业名称不能为空");
        }

        return new EnterpriseName(normalized);
    }

    /**
     * 返回企业名称字符串形式
     *
     * @return 企业名称
     */
    @Override
    public String toString() {
        return name;
    }
}
