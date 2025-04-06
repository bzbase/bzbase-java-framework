package org.bzbase.library.ddd.annotation;

import java.lang.annotation.*;

/**
 * 标识DDD领域的实体
 *
 * @author legendjw
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Entity {
}
