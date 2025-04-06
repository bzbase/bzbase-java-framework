package org.bzbase.library.ddd.type;

/**
 * 可识别的类型，实现此接口的类均可以获取唯一的标识符
 *
 * @author legendjw
 */
public interface Identifiable<I> {
    /**
     * 获取标识符
     *
     * @return 唯一的标识符
     */
    I getId();
}
