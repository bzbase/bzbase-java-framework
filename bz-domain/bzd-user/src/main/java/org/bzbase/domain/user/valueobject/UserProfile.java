package org.bzbase.domain.user.valueobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import org.bzbase.library.ddd.type.ValueObject;
import org.bzbase.primitive.person.*;
import org.bzbase.primitive.user.Nickname;
import org.bzbase.primitive.user.Picture;
import org.bzbase.primitive.user.PreferredUsername;

/**
 * 个人信息
 *
 * @author legendjw
 */
@Value
@Builder
@AllArgsConstructor
@With
public class UserProfile implements ValueObject {
    /**
     * 昵称
     */
    Nickname nickname;

    /**
     * 首选用户名
     */
    PreferredUsername preferredUsername;

    /**
     * 姓名
     */
    Name name;

    /**
     * 头像
     */
    Picture picture;

    /**
     * 性别
     */
    Gender gender;

    /**
     * 出生日期
     */
    Birthdate birthdate;
}
