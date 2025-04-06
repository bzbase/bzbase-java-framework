package org.bzbase.library.ddd.annotation;

import java.lang.annotation.*;

/**
 * 标识一个领域服务
 *
 * @author legendjw
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Service {
}
