package org.bzbase.library.ddd.type;

/**
 * DDD领域的值对象接口，值对象用于描述一类事物，具有不可变性，不包含唯一的业务标识，相等性基于对象的属性。
 */
public interface ValueObject {
    /**
     * 判断两个值对象是否相等，在DDD的概念里值对象的相等性取决于所有的属性是否相等。
     *
     * @param other 其他对象.
     * @return 如果是相同的类且所有属性都相等则返回 true，否则返回 false
     */
    boolean equals(Object other);

    /**
     * 计算值对象的哈希代码，需要根据值对象的所有属性进行计算，
     *
     * @return 此对象的哈希代码
     */
    int hashCode();
}
