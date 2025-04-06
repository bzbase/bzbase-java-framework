package org.bzbase.library.ddd.annotation;

import java.lang.annotation.*;

/**
 * 标识一个资源库，完成聚合的持久化
 *
 * @author legendjw
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Repository {
}
