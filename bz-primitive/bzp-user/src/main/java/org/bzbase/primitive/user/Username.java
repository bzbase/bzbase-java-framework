package org.bzbase.primitive.user;

import lombok.NonNull;
import lombok.Value;
import org.bzbase.library.ddd.type.ValueObject;

/**
 * 用户名，用户登录系统的唯一标识符
 *
 * @author legendjw
 */
@Value
public class Username implements ValueObject {
    /**
     * 用户名
     */
    String value;

    private Username(String value) {
        this.value = value;
    }

    /**
     * 创建一个用户名
     *
     * @param value 用户名的字符串值，不能为空
     * @return 返回一个新的Username对象
     */
    public static Username of(@NonNull String value) {
        return new Username(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
