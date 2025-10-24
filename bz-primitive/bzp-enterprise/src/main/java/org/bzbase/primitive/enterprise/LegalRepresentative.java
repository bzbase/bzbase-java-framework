package org.bzbase.primitive.enterprise;

import lombok.NonNull;
import lombok.Value;
import org.bzbase.library.ddd.type.ValueObject;
import org.bzbase.primitive.person.IdNumber;
import org.bzbase.primitive.person.Name;
import org.bzbase.primitive.phonenumber.PhoneNumber;

/**
 * 法人代表值对象
 */
@Value
public class LegalRepresentative implements ValueObject {
    /**
     * 法人代表姓名
     */
    Name name;
    /**
     * 法人代表身份证号
     */
    IdNumber idNumber;
    /**
     * 法人代表联系电话
     */
    PhoneNumber phoneNumber;

    /**
     * 使用给定信息创建法人代表
     *
     * @param name 姓名
     * @param idNumber 身份证号
     * @param phoneNumber 联系电话
     */
    private LegalRepresentative(Name name, IdNumber idNumber, PhoneNumber phoneNumber) {
        this.name = name;
        this.idNumber = idNumber;
        this.phoneNumber = phoneNumber;
    }

    /**
     * 创建法人代表值对象
     *
     * @param name 姓名
     * @param idNumber 身份证号
     * @param phoneNumber 联系电话
     * @return 法人代表值对象
     */
    public static LegalRepresentative of(@NonNull Name name,
                                         @NonNull IdNumber idNumber,
                                         @NonNull PhoneNumber phoneNumber) {
        return new LegalRepresentative(name, idNumber, phoneNumber);
    }
}
