package org.bzbase.library.ddd.type;

/**
 * 实现资源库接口的抽象资源库，所有的资源库类可以继承自此抽象类来获取通用实现
 *
 * @param <A> 聚合根类型
 * @param <I> 聚合根标识符类型
 *
 * @author legendjw
 */
public abstract class AbstractRepository<A extends AggregateRoot<I>, I> implements Repository<A, I> {
}
