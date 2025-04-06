package org.bzbase.library.ddd.type;

import java.util.Objects;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 实现DDD领域实体接口的抽象实体，所有的实体类可以继承自此抽象类来获取通用实现
 *
 * @param <I> 标识符类，常用String、Long或者自定义值对象类
 *
 * @author legendjw
 */
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class AbstractEntity<I> implements Entity<I> {
    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        return Objects.equals(getId(), ((Entity<?>) other).getId());
    }
}
