package org.bzbase.library.ddd.type;

/**
 * 单值值对象接口
 * 表示只包含一个值的值对象，提供获取该值的方法
 * 
 * @param <T> 值的类型
 * @author legendjw
 */
public interface SingleValueObject<T> extends ValueObject {
    
    /**
     * 获取值对象中的值
     * 
     * @return 值对象包含的值
     */
    T getValue();
} 