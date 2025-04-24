package org.bzbase.library.ddd.specification;

/**
 * NOT规约实现
 * @param <T> 规约应用的目标类型
 * 
 * @author legendjw
 */
public class NotSpecification<T> extends AbstractSpecification<T> {
    
    private final Specification<T> specification;
    
    public NotSpecification(Specification<T> specification) {
        this.specification = specification;
    }
    
    @Override
    public boolean isSatisfiedBy(T candidate) {
        return !specification.isSatisfiedBy(candidate);
    }
} 