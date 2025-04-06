package org.bzbase.domain.organization.valueobject;

import org.bzbase.primitive.emailaddress.EmailAddress;
import org.bzbase.primitive.person.Birthdate;
import org.bzbase.primitive.person.Gender;
import org.bzbase.primitive.phonenumber.PhoneNumber;
import org.bzbase.primitive.user.Picture;

import lombok.Builder;
import lombok.Value;
import lombok.With;

/**
 * 员工基本信息值对象
 */
@Value
@With
@Builder
public class EmployeeBasicInfo {
    /**
     * 姓名
     */
    private String name;

    /**
     * 头像
     */
    private Picture picture;

    /**
     * 性别
     */
    private Gender gender;

    /**
     * 出生日期
     */
    private Birthdate birthDate;

    /**
     * 邮箱
     */
    private EmailAddress emailAddress;

    /**
     * 电话
     */
    private PhoneNumber phoneNumber;

    /**
     * 家庭住址
     */
    private String address;
} 
