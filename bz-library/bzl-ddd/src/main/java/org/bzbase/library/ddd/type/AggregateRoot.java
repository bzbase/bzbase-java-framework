package org.bzbase.library.ddd.type;

/**
 * DDD领域的聚合根接口
 *
 * @param <I> 标识符类，常用String、Long或者自定义值对象类
 *
 * @author legendjw
 */
public interface AggregateRoot<I> extends Entity<I> {
}
