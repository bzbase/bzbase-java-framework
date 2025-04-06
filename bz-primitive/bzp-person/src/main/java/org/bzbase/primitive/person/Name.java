package org.bzbase.primitive.person;

import lombok.Value;
import org.bzbase.library.ddd.type.ValueObject;

/**
 * 姓名，用户的真实姓名
 *
 * @author legendjw
 */
@Value
public class Name implements ValueObject {
    /**
     * 全名
     */
    String name;

    /**
     * 姓氏
     */
    String familyName;

    /**
     * 名字
     */
    String givenName;

    /**
     * 中间名
     */
    String middleName;

    private Name(String name, String familyName, String givenName, String middleName) {
        this.name = name;
        this.familyName = familyName;
        this.givenName = givenName;
        this.middleName = middleName;
    }

    /**
     * 根据全名创建一个姓名
     *
     * @param name 全名
     * @return 返回一个新的Name对象
     */
    public static Name of(String name) {
        return new Name(name, null, null, null);
    }

    /**
     * 根据姓氏、名字、中间名创建一个姓名
     *
     * @param familyName 姓氏
     * @param givenName 名字
     * @param middleName 中间名
     * @return 返回一个新的Name对象
     */
    public static Name of(String familyName, String givenName, String middleName) {
        return new Name(familyName + " " + givenName + " " + middleName, familyName, givenName, middleName);
    }
}
