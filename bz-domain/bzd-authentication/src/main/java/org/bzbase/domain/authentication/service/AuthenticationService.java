package org.bzbase.domain.authentication.service;

import org.bzbase.domain.authentication.Authentication;
import org.bzbase.library.security.identity.Credentials;

/**
 * 认证领域服务
 *
 * @author legendjw
 */
public interface AuthenticationService {
    /**
     * 对指定凭据进行认证
     *
     * @param credentials 认证凭据
     * @return 认证结果
     */
    Authentication authenticate(Credentials credentials);
}
