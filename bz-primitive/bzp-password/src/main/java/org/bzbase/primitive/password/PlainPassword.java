package org.bzbase.primitive.password;

import java.util.HashMap;
import java.util.Map;

import org.bzbase.library.ddd.type.ValueObject;
import org.bzbase.primitive.password.rule.CommonPasswordPolicy;
import org.bzbase.primitive.password.rule.PasswordRule;
import org.bzbase.primitive.password.rule.ValidationResult;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;

import lombok.NonNull;
import lombok.Value;

/**
 * 原始明文密码值对象，表示用户输入的原始明文密码，不应该在任何地方存储明文。
 *
 * <p>可以结合 {@link PasswordRule} 密码规则类进行密码复杂度验证。常用的密码策略规则也可以直接使用 {@link CommonPasswordPolicy}。
 *
 * <p>支持获取密码强度，通过调用 {@link #getStrength()} 可以获取此明文密码的强度枚举值。
 *
 * @author legendjw
 */
@Value
public class PlainPassword implements ValueObject {
    /**
     * 内部使用的密码强度检测库
     */
    private static final Zxcvbn zxcvbn = new Zxcvbn();

    /**
     * 明文密码
     */
    String plainText;

    private PlainPassword(String plainText) {
        this.plainText = plainText;
    }

    /**
     * 创建一个明文密码对象
     *
     * @param plainText 非空的明文密码字符串
     * @return 明文密码对象
     */
    public static PlainPassword of(@NonNull String plainText) {
        return new PlainPassword(plainText);
    }

    /**
     * 创建通过密码规则的明文密码对象
     *
     * @param plainText 明文密码，不能为空
     * @param rule 密码规则，不能为空
     * @return 明文密码对象
     * @throws InvalidPasswordException 如果密码验证失败，则抛出此异常，包含验证失败的原因
     * @see CommonPasswordPolicy
     */
    public static PlainPassword of(@NonNull String plainText, @NonNull PasswordRule rule) throws InvalidPasswordException {
        ValidationResult validate = rule.validate(plainText);
        if (validate.isInvalid()) {
            throw new InvalidPasswordException(String.format("密码验证失败: %s", validate.getErrorMessage()));
        }
        return new PlainPassword(plainText);
    }

    /**
     * 获取明文密码的强度
     *
     * @return 密码强度的枚举值，表示密码的复杂度（如：WEAK, FAIR, GOOD, STRONG, VERY_STRONG）
     */
    public PasswordStrength getStrength() {
        Strength strength = zxcvbn.measure(plainText);
        Map<Integer, PasswordStrength> strengthMap = new HashMap<>();
        strengthMap.put(0, PasswordStrength.WEAK);
        strengthMap.put(1, PasswordStrength.FAIR);
        strengthMap.put(2, PasswordStrength.GOOD);
        strengthMap.put(3, PasswordStrength.STRONG);
        strengthMap.put(4, PasswordStrength.VERY_STRONG);
        return strengthMap.get(strength.getScore());
    }

    @Override
    public String toString() {
        return plainText;
    }
}
