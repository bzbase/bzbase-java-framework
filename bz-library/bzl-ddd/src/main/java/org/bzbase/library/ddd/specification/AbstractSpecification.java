package org.bzbase.library.ddd.specification;

/**
 * 规约模式的抽象基类
 * @param <T> 规约应用的目标类型
 * 
 * @author legendjw
 */
public abstract class AbstractSpecification<T> implements Specification<T> {
    
    @Override
    public Specification<T> and(Specification<T> other) {
        return new AndSpecification<>(this, other);
    }
    
    @Override
    public Specification<T> or(Specification<T> other) {
        return new OrSpecification<>(this, other);
    }
    
    @Override
    public Specification<T> not() {
        return new NotSpecification<>(this);
    }
} 