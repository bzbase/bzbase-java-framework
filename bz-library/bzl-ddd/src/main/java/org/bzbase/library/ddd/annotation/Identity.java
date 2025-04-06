package org.bzbase.library.ddd.annotation;

import java.lang.annotation.*;

/**
 * 标识一个类的唯一标识属性，常用于聚合根或者实体
 *
 * @author legendjw
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
public @interface Identity {
}
