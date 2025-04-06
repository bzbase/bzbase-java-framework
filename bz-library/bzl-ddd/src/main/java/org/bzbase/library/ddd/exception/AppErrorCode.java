package org.bzbase.library.ddd.exception;

import lombok.Getter;

/**
 * 应用模块错误码
 *
 * @author legendjw
 */
@Getter
public class AppErrorCode implements ErrorCode {
    /**
     * 应用标示
     */
    private final String app;
    /**
     * 模块标示
     */
    private final String module;
    /**
     * 错误码
     */
    private final String code;

    public AppErrorCode(String app, String module, String code) {
        this.app = app;
        this.module = module;
        this.code = code;
    }

    public String getErrorCode() {
        return String.format("%s.%s.%s", app, module, code);
    }
}
