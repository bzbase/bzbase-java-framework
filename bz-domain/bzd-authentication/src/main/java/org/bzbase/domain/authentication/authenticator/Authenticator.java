package org.bzbase.domain.authentication.authenticator;

import org.bzbase.library.security.identity.Credentials;
import org.bzbase.library.security.identity.Identity;

/**
 * 提供身份认证服务的接口
 *
 * <p>
 * 主要负责验证用户提供的身份识别信息是否正确，比如可以使用用户名密码验证服务通过验证用户名以及密码的正确性来识别确认用户身份。
 *
 * @author legendjw
 */
public interface Authenticator {
    /**
     * 获取认证类型
     *
     * @return 认证类型
     */
    String getAuthenticationType();

    /**
     * 对身份凭据进行认证
     * 
     * @param credentials 身份凭据
     * @return 认证身份
     * @throws AuthenticationException 认证失败异常
     */
    Identity authenticate(Credentials credentials) throws AuthenticationException;

    /**
     * 是否支持指定的认证凭据
     *
     * @param credentials 认证凭据
     * @return true 如果支持，否则返回 false
     */
    boolean supports(Class<?> credentials);
}
