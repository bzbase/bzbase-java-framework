package org.bzbase.library.ddd.exception;

/**
 * 错误码接口
 *
 * @author legendjw
 * @since 1.0.0
 */
public interface ErrorCode {
    /**
     * 获取错误编码
     *
     * @return 返回唯一的错误编码
     */
    String getErrorCode();
}
