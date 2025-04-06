package org.bzbase.domain.organization.valueobject;

import org.bzbase.library.ddd.type.ValueObject;
import org.bzbase.primitive.phonenumber.PhoneNumber;

import lombok.Builder;
import lombok.Value;

/**
 * 紧急联系人值对象
 */
@Value
@Builder
public class EmergencyContact implements ValueObject {

    /**
     * 联系人姓名
     */
    String name;

    /**
     * 联系人电话
     */
    PhoneNumber phoneNumber;

    /**
     * 与联系人关系
     */
    String relationship;
}