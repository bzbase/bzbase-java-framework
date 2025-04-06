package org.bzbase.primitive.user;

import lombok.NonNull;
import lombok.Value;
import org.bzbase.library.ddd.type.ValueObject;

/**
 * 希望被称呼的首选用户名，介于真实姓名(Name)和昵称(Nickname)之间
 *
 * @author legendjw
 */
@Value
public class PreferredUsername implements ValueObject {
    /**
     * 用户名
     */
    String value;

    private PreferredUsername(String value) {
        this.value = value;
    }

    /**
     * 创建一个首选用户名
     *
     * @param value 用户名的字符串值，不能为空
     * @return 返回一个新的PreferredUsername对象
     */
    public static PreferredUsername of(@NonNull String value) {
        return new PreferredUsername(value);
    }
}
