package org.bzbase.library.ddd.type;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

/**
 * 单值值对象抽象实现类
 * 实现了单值值对象接口，提供通用的相等性比较和哈希值计算
 * 
 * @param <T> 值的类型
 * @author legendjw
 */
@Getter
@EqualsAndHashCode
public abstract class AbstractSingleValueObject<T> implements SingleValueObject<T>, Serializable {
    protected final T value;
    
    protected AbstractSingleValueObject(T value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return value != null ? value.toString() : "";
    }
    
    /**
     * 检查值是否为空
     * 
     * @return 如果值为null则返回true，否则返回false
     */
    public boolean isEmpty() {
        return value == null;
    }
} 