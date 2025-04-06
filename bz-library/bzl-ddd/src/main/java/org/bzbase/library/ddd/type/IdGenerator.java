package org.bzbase.library.ddd.type;

/**
 * ID生成器
 * 
 * @author legendjw
 */
public interface IdGenerator<T> {
	/**
	 * 生成ID
	 * 
	 * @return ID
	 */
	T generate();
}
