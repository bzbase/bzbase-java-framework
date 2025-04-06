package org.bzbase.library.ddd.exception;

import lombok.Getter;

/**
 * 模块错误码
 *
 * @author legendjw
 */
@Getter
public class ModuleErrorCode implements ErrorCode {
    /**
     * 模块标示
     */
    private final String module;
    /**
     * 错误码
     */
    private final String code;

    public ModuleErrorCode(String module, String code) {
        this.module = module;
        this.code = code;
    }

    public String getErrorCode() {
        return String.format("%s.%s", module, code);
    }
}
