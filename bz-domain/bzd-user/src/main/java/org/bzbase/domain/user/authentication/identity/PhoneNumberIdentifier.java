package org.bzbase.domain.user.authentication.identity;

import org.bzbase.library.security.identity.Identifier;
import org.bzbase.primitive.phonenumber.PhoneNumber;

import lombok.Value;

/**
 * 手机号身份标识
 *
 * @author legendjw
 */
@Value
public class PhoneNumberIdentifier implements Identifier {
    public static final String TYPE = "PHONE_NUMBER";
    public static final String TYPE_NAME = "手机号";

    PhoneNumber phoneNumber;

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String getTypeName() {
        return TYPE_NAME;
    }

    @Override
    public String getValue() {
        return phoneNumber.toE164Format();
    }

    /**
     * 创建一个手机号标识
     *
     * @param phoneNumber 手机号
     * @return 手机号标识
     */
    public static PhoneNumberIdentifier of(String phoneNumber) {
        return new PhoneNumberIdentifier(PhoneNumber.of(phoneNumber));
    }
}
