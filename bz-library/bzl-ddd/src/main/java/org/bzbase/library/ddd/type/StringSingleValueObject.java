package org.bzbase.library.ddd.type;

/**
 * 字符串单值值对象抽象类
 * 专门用于处理字符串类型的单值值对象
 * 
 * @author legendjw
 */
public abstract class StringSingleValueObject extends AbstractSingleValueObject<String> {
    /**
     * 构造函数
     * 
     * @param value 字符串值
     */
    protected StringSingleValueObject(String value) {
        super(value);
    }
} 