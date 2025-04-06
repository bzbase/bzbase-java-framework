package org.bzbase.library.security.identity;

import java.util.Set;

import org.bzbase.library.security.claim.Claim;

/**
 * 认证身份
 * 
 * @author legendjw
 */
public interface Identity {
	/**
	 * 获取认证类型
	 * 
	 * @return 认证类型
	 */
	String getAuthenticationType();

	/**
	 * 获取身份标识
	 * 
	 * @return 身份标识
	 */
	Identifier getIdentifier();

	/**
	 * 获取身份声明信息集合
	 * 
	 * @return 身份声明信息集合
	 */
	Set<Claim> getClaims();
}