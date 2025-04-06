package org.bzbase.primitive.user;

import lombok.NonNull;
import lombok.Value;
import org.bzbase.library.ddd.type.ValueObject;

/**
 * 昵称，非正式个性化的称呼
 *
 * @author legendjw
 */
@Value
public class Nickname implements ValueObject {
    /**
     * 昵称
     */
    String value;

    private Nickname(String value) {
        this.value = value;
    }

    /**
     * 创建一个昵称
     *
     * @param value 昵称的字符串值，不能为空
     * @return 返回一个新的Nickname对象
     */
    public static Nickname of(@NonNull String value) {
        return new Nickname(value);
    }
}
