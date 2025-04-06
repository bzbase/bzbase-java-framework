package org.bzbase.domain.user.authentication.identity;

import org.bzbase.library.security.identity.Identifier;
import org.bzbase.primitive.user.Username;

import lombok.Value;

/**
 * 用户名本地身份
 *
 * @author legendjw
 */
@Value
public class UsernameIdentifier implements Identifier {
    public static final String TYPE = "USERNAME";
    public static final String TYPE_NAME = "用户名";

    Username username;

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
        return username.getValue();
    }

    /**
     * 创建一个用户名标识
     *
     * @param username 用户名
     * @return 用户名标识
     */
    public static UsernameIdentifier of(String username) {
        return new UsernameIdentifier(Username.of(username));
    }
}
