package org.bzbase.domain.user.authentication.identity;

import org.bzbase.library.security.identity.Identifier;
import org.bzbase.primitive.emailaddress.EmailAddress;

import lombok.Value;

/**
 * 邮箱地址本地身份
 *
 * @author legendjw
 */
@Value
public class EmailAddressIdentifier implements Identifier {
    public static final String TYPE = "EMAIL_ADDRESS";
    public static final String TYPE_NAME = "邮箱地址";

    EmailAddress emailAddress;

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
        return emailAddress.getAddress();
    }

    /**
     * 创建一个邮箱地址标识
     *
     * @param emailAddress 邮箱地址
     * @return 邮箱地址标识
     */
    public static EmailAddressIdentifier of(String emailAddress) {
        return new EmailAddressIdentifier(EmailAddress.of(emailAddress));
    }
}
