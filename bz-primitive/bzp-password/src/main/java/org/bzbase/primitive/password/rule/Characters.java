package org.bzbase.primitive.password.rule;

import lombok.Getter;

/**
 * 密码规则用到的字符集合
 *
 * @author legendjw
 */
@Getter
public enum Characters {
    // 数字
    DIGITS("0123456789"),
    // 小写字母
    LOWERCASE_LETTERS("abcdefghijklmnopqrstuvwxyz"),
    // 大写字母
    UPPERCASE_LETTERS("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
    // 大小写字母
    LETTERS(LOWERCASE_LETTERS.getCharacters() + UPPERCASE_LETTERS.getCharacters()),
    // 特殊字符
    SPECIAL_CHARACTERS("!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~"),
    // 数字或者大小写字母
    DIGITS_OR_LETTERS(DIGITS.getCharacters() + LETTERS.getCharacters()),
    // 数字、大小写字母或者特殊字符
    DIGITS_OR_LETTERS_OR_SPECIALS(DIGITS.getCharacters() + LETTERS.getCharacters() + SPECIAL_CHARACTERS.getCharacters());

    private final String characters;

    Characters(java.lang.String characters) {
        this.characters = characters;
    }

    /**
     * 是否包含指定的字符
     *
     * @param c 检查字符
     * @return true 如果包含指定的字符，否则返回 false
     */
    public boolean contains(int c) {
        return characters.indexOf(c) >= 0;
    }
}
