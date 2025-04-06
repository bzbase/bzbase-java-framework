package org.bzbase.primitive.password.rule;

/**
 * 密码策略规则
 *
 * @author legendjw
 */
public interface PasswordRule {
    ValidationResult validate(String plainPassword);
}
