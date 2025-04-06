package org.bzbase.library.ddd.annotation;

import java.lang.annotation.*;

/**
 * 标识DDD领域的聚合根，即聚合的根实体
 *
 * @author legendjw
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Entity
public @interface AggregateRoot {
}
