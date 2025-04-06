package org.bzbase.library.security.claim;

/**
 * 声明值的数据类型
 *
 * @author legendjw
 */
public class ClaimValueType {
	private ClaimValueType() {
	}

	/**
	 * 字符串类型
	 */
	public static final String STRING = "string";

	/**
	 * 布尔类型
	 */
	public static final String BOOLEAN = "boolean";

	/**
	 * 数值类型
	 */
	public static final String NUMBER = "number";

	/**
	 * 日期时间类型（ISO 8601格式）
	 */
	public static final String DATETIME = "datetime";

	/**
	 * JSON对象类型
	 */
	public static final String JSON_OBJECT = "object";

	/**
	 * JSON数组类型
	 */
	public static final String JSON_ARRAY = "array";
}
