package org.bzbase.primitive.enterprise;

import lombok.NonNull;
import lombok.Value;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.ValueObject;

import java.util.Locale;

/**
 * 统一社会信用代码值对象
 */
@Value
public class UnifiedSocialCreditCode implements ValueObject {
    /**
     * 统一社会信用代码允许出现的字符集合
     */
    private static final String AVAILABLE_CHARS = "0123456789ABCDEFGHJKLMNPQRTUWXY";
    /**
     * 统一社会信用代码固定长度
     */
    private static final int CODE_LENGTH = 18;
    /**
     * 统一社会信用代码字符串
     */
    String value;

    /**
     * 使用预处理后的字符串创建值对象
     *
     * @param value 统一社会信用代码
     */
    private UnifiedSocialCreditCode(String value) {
        this.value = value;
    }

    /**
     * 创建统一社会信用代码对象并进行格式与校验位验证
     *
     * @param code 原始统一社会信用代码
     * @return 统一社会信用代码值对象
     */
    public static UnifiedSocialCreditCode of(@NonNull String code) {
        String normalized = code.trim().toUpperCase(Locale.ROOT);
        if (normalized.length() != CODE_LENGTH) {
            throw new DomainException("统一社会信用代码必须为18位字符");
        }

        if (!containsOnlyLegalCharacters(normalized)) {
            throw new DomainException("统一社会信用代码包含非法字符");
        }

        return new UnifiedSocialCreditCode(normalized);
    }

    /**
     * 校验统一社会信用代码是否仅包含合法字符
     *
     * @param code 统一社会信用代码
     * @return true 表示符合要求
     */
    private static boolean containsOnlyLegalCharacters(String code) {
        for (char ch : code.toCharArray()) {
            if (AVAILABLE_CHARS.indexOf(ch) < 0) {
                return false;
            }
        }
        return true;
    }
}
