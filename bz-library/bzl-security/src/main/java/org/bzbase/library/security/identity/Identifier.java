package org.bzbase.library.security.identity;

/**
 * 身份标识符
 *
 * @author legendjw
 */
public interface Identifier {
	/**
	 * 获取标识符类型
	 *
	 * @return 标识符类型
	 */
	String getType();

	/**
	 * 获取标识符类型名称
	 *
	 * @return 标识符类型名称
	 */
	String getTypeName();

	/**
	 * 获取标识符
	 *
	 * @return 标识符
	 */
    String getValue();
}
