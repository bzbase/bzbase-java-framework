package org.bzbase.library.security.claim;

/**
 * 标准声明类型。除自定义的声明类型外，与 OpenID Connect 标准声明类型一致。
 * 参考：https://openid.net/specs/openid-connect-core-1_0.html#StandardClaims
 * 
 * @author legendjw
 */
public class ClaimType {
	private ClaimType() {
	}

	/**
	 * Subject - 主体标识符。在颁发者上下文中唯一且永久的标识符。
	 */
	public static final String SUB = "sub";

	/**
	 * 用户的全名
	 */
	public static final String NAME = "name";

	/**
	 * 用户的电子邮件地址
	 */
	public static final String EMAIL = "email";

	/**
	 * 电子邮件是否已验证
	 */
	public static final String EMAIL_VERIFIED = "email_verified";

	/**
	 * 用户的手机号，E.164格式
	 */
	public static final String PHONE_NUMBER = "phone_number";

	/**
	 * 手机号是否已验证
	 */
	public static final String PHONE_NUMBER_VERIFIED = "phone_number_verified";

	/**
	 * 用户的首选用户名
	 */
	public static final String PREFERRED_USERNAME = "preferred_username";

	/**
	 * 用户的头像URL
	 */
	public static final String PICTURE = "picture";

	/**
	 * 用户的性别
	 */
	public static final String GENDER = "gender";

	/**
	 * 用户的生日，ISO 8601:2004 YYYY-MM-DD 格式
	 */
	public static final String BIRTHDATE = "birthdate";

	/**
	 * 用户的时区
	 */
	public static final String ZONEINFO = "zoneinfo";

	/**
	 * 用户的语言，BCP47[RFC5646] 格式
	 */
	public static final String LOCALE = "locale";

	/**
	 * 用户的地址信息，JSON对象
	 */
	public static final String ADDRESS = "address";

	/**
	 * 用户信息更新时间，Unix时间戳
	 */
	public static final String UPDATED_AT = "updated_at";
}