package org.bzbase.library.ddd.type;

/**
 * DDD领域的实体接口，实体在不同生命周期属性会改变，需要提供一个标识符来确定同一实体。
 *
 * @param <I> 标识符类，常用String、Long或者自定义值对象类
 *
 * @author legendjw
 */
public interface Entity<I> extends Identifiable<I> {
    /**
     * 判断两个实体否相等，在DDD的概念里实体的相等性取决于唯一标识是否相等。
     *
     * @param other 其他对象.
     * @return 如果是相同的类且唯一标识相等则返回 true，否则返回 false
     */
    boolean equals(Object other);

    /**
     * 计算值实体的哈希代码，需要根据实体的唯一标识进行计算，
     *
     * @return 此对象的哈希代码
     */
    int hashCode();
}
