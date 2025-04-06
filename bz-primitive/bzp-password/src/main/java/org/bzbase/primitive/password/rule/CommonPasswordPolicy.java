package org.bzbase.primitive.password.rule;

import org.bzbase.primitive.password.Module;
import org.bzbase.primitive.password.rule.*;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 常用的密码策略
 *
 * @author legendjw
 */
public class CommonPasswordPolicy {
    // 6位数字
    public static final PasswordRule SIX_DIGIT = new CompositeRule(
            new LengthRule(6),
            new OnlyAllowedCharacterRule(Characters.DIGITS, Module.getMessageSource().getMessage("OnlyAllowedCharacterRule.onlyDigitError"))
    );

    // 至少8位
    public static final PasswordRule AT_LEAST_EIGHT = new CompositeRule(
            new LengthRule(8, Integer.MAX_VALUE),
            new OnlyAllowedCharacterRule(Characters.DIGITS_OR_LETTERS_OR_SPECIALS)
    );

    // 8-20位，至少包含英文字母，数字，特殊符号中的2种
    public static final PasswordRule AT_LEAST_TWO_OF_LETTER_DIGIT_SPECIAL = new CompositeRule(
            new LengthRule(8, 20),
            new OnlyAllowedCharacterRule(Characters.DIGITS_OR_LETTERS_OR_SPECIALS),
            new AtLeastOfRule(new HashSet<>(Arrays.asList(
                    new AtLeastCharacterRule(Characters.LETTERS),
                    new AtLeastCharacterRule(Characters.DIGITS),
                    new AtLeastCharacterRule(Characters.SPECIAL_CHARACTERS)
            )), 2, Module.getMessageSource().getMessage("AtLeastOfRule.atLeastTwoOfLetterDigitSpecialError"))
    );

    // 8-20位，至少包含大写字母，小写字母，数字，特殊符号中的3种
    public static final PasswordRule AT_LEAST_THREE_OF_UPPER_LOWER_DIGIT_SPECIAL = new CompositeRule(
            new LengthRule(8, 20),
            new OnlyAllowedCharacterRule(Characters.DIGITS_OR_LETTERS_OR_SPECIALS),
            new AtLeastOfRule(new HashSet<>(Arrays.asList(
                    new AtLeastCharacterRule(Characters.LOWERCASE_LETTERS),
                    new AtLeastCharacterRule(Characters.UPPERCASE_LETTERS),
                    new AtLeastCharacterRule(Characters.DIGITS),
                    new AtLeastCharacterRule(Characters.SPECIAL_CHARACTERS)
            )), 3, Module.getMessageSource().getMessage("AtLeastOfRule.atLeastThreeOfUpperLowerDigitSpecialError"))
    );

    // 8-20位，至少包含一个英文字母，一个数字
    public static final PasswordRule AT_LEAST_ONE_LETTER_DIGIT = new CompositeRule(
            new LengthRule(8, 20),
            new OnlyAllowedCharacterRule(Characters.DIGITS_OR_LETTERS_OR_SPECIALS),
            new AtLeastCharacterRule(Characters.LETTERS, Module.getMessageSource().getMessage("AtLeastCharacterRule.atLeastOneLetterError")),
            new AtLeastCharacterRule(Characters.DIGITS, Module.getMessageSource().getMessage("AtLeastCharacterRule.atLeastOneDigitError"))
    );

    // 8-20位，至少包含一个大写字母，一个小写字母，一个数字
    public static final PasswordRule AT_LEAST_ONE_UPPER_LOWER_DIGIT = new CompositeRule(
            new LengthRule(8, 20),
            new OnlyAllowedCharacterRule(Characters.DIGITS_OR_LETTERS_OR_SPECIALS),
            new AtLeastCharacterRule(Characters.UPPERCASE_LETTERS, Module.getMessageSource().getMessage("AtLeastCharacterRule.atLeastOneUpperError")),
            new AtLeastCharacterRule(Characters.LOWERCASE_LETTERS, Module.getMessageSource().getMessage("AtLeastCharacterRule.atLeastOneLowerError")),
            new AtLeastCharacterRule(Characters.DIGITS, Module.getMessageSource().getMessage("AtLeastCharacterRule.atLeastOneDigitError"))
    );

    // 8-20位，至少包含一个英文字母，一个数字，一个特殊符号
    public static final PasswordRule AT_LEAST_ONE_LETTER_DIGIT_SPECIAL = new CompositeRule(
            new LengthRule(8, 20),
            new OnlyAllowedCharacterRule(Characters.DIGITS_OR_LETTERS_OR_SPECIALS),
            new AtLeastCharacterRule(Characters.LETTERS, Module.getMessageSource().getMessage("AtLeastCharacterRule.atLeastOneLetterError")),
            new AtLeastCharacterRule(Characters.DIGITS, Module.getMessageSource().getMessage("AtLeastCharacterRule.atLeastOneDigitError")),
            new AtLeastCharacterRule(Characters.SPECIAL_CHARACTERS, Module.getMessageSource().getMessage("AtLeastCharacterRule.atLeastOneSpecialError"))
    );

    // 8-20位，至少包含一个大写字母，一个小写字母，一个数字，一个特殊符号
    public static final PasswordRule AT_LEAST_ONE_UPPER_LOWER_DIGIT_SPECIAL = new CompositeRule(
            new LengthRule(8, 20),
            new OnlyAllowedCharacterRule(Characters.DIGITS_OR_LETTERS_OR_SPECIALS),
            new AtLeastCharacterRule(Characters.UPPERCASE_LETTERS, Module.getMessageSource().getMessage("AtLeastCharacterRule.atLeastOneUpperError")),
            new AtLeastCharacterRule(Characters.LOWERCASE_LETTERS, Module.getMessageSource().getMessage("AtLeastCharacterRule.atLeastOneLowerError")),
            new AtLeastCharacterRule(Characters.DIGITS, Module.getMessageSource().getMessage("AtLeastCharacterRule.atLeastOneDigitError")),
            new AtLeastCharacterRule(Characters.SPECIAL_CHARACTERS, Module.getMessageSource().getMessage("AtLeastCharacterRule.atLeastOneSpecialError"))
    );
}
