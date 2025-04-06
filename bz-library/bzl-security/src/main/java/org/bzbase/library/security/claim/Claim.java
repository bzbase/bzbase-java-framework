package org.bzbase.library.security.claim;

import lombok.NonNull;
import lombok.Value;

/**
 * 表示关于主体的声明信息。
 * Claim 是一个关于主体的声明，由某个实体（颁发者）做出。
 * 例如，用户的姓名、年龄、角色等都可以是一个声明。
 * 
 * @author legendjw
 */
@Value
public class Claim {
	/**
	 * 声明的类型。
	 */
	String type;

	/**
	 * 声明的值。
	 */
	String value;

	/**
	 * 声明值的类型。
	 */
	String valueType;

	/**
	 * 声明的颁发者。
	 */
	String issuer;

	/**
	 * 声明的原始颁发者。
	 */
	String originalIssuer;

	private Claim(String type, String value, String valueType, String issuer, String originalIssuer) {
		this.type = type;
		this.value = value;
		this.valueType = valueType;
		this.issuer = issuer;
		this.originalIssuer = originalIssuer;
	}

	/**
	 * 使用指定的声明类型和值初始化新的 Claim 实例。
	 * 默认使用string作为值类型。
	 *
	 * @param type  声明的类型
	 * @param value 声明的值
	 */
	public static Claim of(@NonNull String type, @NonNull String value) {
		return new Claim(type, value, ClaimValueType.STRING, null, null);
	}

	/**
	 * 使用指定的声明类型、值和值类型初始化新的 Claim 实例。
	 *
	 * @param type      声明的类型
	 * @param value     声明的值
	 * @param valueType 声明值的类型
	 */
	public static Claim of(@NonNull String type, @NonNull String value, @NonNull String valueType) {
		return new Claim(type, value, valueType, null, null);
	}

	/**
	 * 使用指定的声明类型、值、值类型和颁发者初始化新的 Claim 实例。
	 *
	 * @param type      声明的类型
	 * @param value     声明的值
	 * @param valueType 声明值的类型
	 * @param issuer    声明的颁发者
	 */
	public static Claim ofWithValueTypeAndIssuer(@NonNull String type, @NonNull String value, @NonNull String valueType,
			@NonNull String issuer) {
		return new Claim(type, value, valueType, issuer, null);
	}

	/**
	 * 使用指定的声明类型、值、值类型、颁发者和原始颁发者初始化新的 Claim 实例。
	 *
	 * @param type           声明的类型
	 * @param value          声明的值
	 * @param valueType      声明值的类型
	 * @param issuer         声明的颁发者
	 * @param originalIssuer 声明的原始颁发者
	 */
	public static Claim ofWithAll(@NonNull String type, @NonNull String value, @NonNull String valueType,
			@NonNull String issuer, @NonNull String originalIssuer) {
		return new Claim(type, value, valueType, issuer, originalIssuer);
	}
}