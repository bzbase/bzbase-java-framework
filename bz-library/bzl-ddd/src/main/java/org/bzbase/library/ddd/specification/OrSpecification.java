package org.bzbase.library.ddd.specification;

/**
 * OR组合规约实现
 * @param <T> 规约应用的目标类型
 * 
 * @author legendjw
 */
public class OrSpecification<T> extends AbstractSpecification<T> {
    
    private final Specification<T> left;
    private final Specification<T> right;
    
    public OrSpecification(Specification<T> left, Specification<T> right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public boolean isSatisfiedBy(T candidate) {
        return left.isSatisfiedBy(candidate) || right.isSatisfiedBy(candidate);
    }
} 