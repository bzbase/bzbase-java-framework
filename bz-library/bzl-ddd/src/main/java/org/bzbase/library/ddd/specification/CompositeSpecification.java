package org.bzbase.library.ddd.specification;

/**
 * 组合规约工具类，提供静态工厂方法
 * 
 * @author legendjw
 */
public final class CompositeSpecification {
    
    private CompositeSpecification() {
        // 工具类禁止实例化
    }
    
    /**
     * 创建一个永远满足的规约
     * @param <T> 规约应用的目标类型
     * @return 永远返回true的规约
     */
    public static <T> Specification<T> alwaysTrue() {
        return new AbstractSpecification<T>() {
            @Override
            public boolean isSatisfiedBy(T candidate) {
                return true;
            }
        };
    }
    
    /**
     * 创建一个永远不满足的规约
     * @param <T> 规约应用的目标类型
     * @return 永远返回false的规约
     */
    public static <T> Specification<T> alwaysFalse() {
        return new AbstractSpecification<T>() {
            @Override
            public boolean isSatisfiedBy(T candidate) {
                return false;
            }
        };
    }
    
    /**
     * 创建一个基于谓词函数的规约
     * @param <T> 规约应用的目标类型
     * @param predicate 谓词函数接口
     * @return 基于谓词函数的规约
     */
    public static <T> Specification<T> of(java.util.function.Predicate<T> predicate) {
        return new AbstractSpecification<T>() {
            @Override
            public boolean isSatisfiedBy(T candidate) {
                return predicate.test(candidate);
            }
        };
    }
} 