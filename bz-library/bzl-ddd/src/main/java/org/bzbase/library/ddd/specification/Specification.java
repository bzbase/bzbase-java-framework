package org.bzbase.library.ddd.specification;

/**
 * 规约模式核心接口
 * @param <T> 规约应用的目标类型
 * 
 * @author legendjw
 */
public interface Specification<T> {
    
    /**
     * 检查候选对象是否满足规约
     * @param candidate 候选对象
     * @return 是否满足规约
     */
    boolean isSatisfiedBy(T candidate);
    
    /**
     * 与操作
     * @param other 另一个规约
     * @return 组合后的规约
     */
    Specification<T> and(Specification<T> other);
    
    /**
     * 或操作
     * @param other 另一个规约
     * @return 组合后的规约
     */
    Specification<T> or(Specification<T> other);
    
    /**
     * 非操作
     * @return 取反后的规约
     */
    Specification<T> not();
} 