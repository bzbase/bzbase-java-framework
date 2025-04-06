package org.bzbase.primitive.password.rule;

import lombok.NonNull;
import lombok.Value;

/**
 * 至少包含指定数量字符的规则，使用场景比如至少包含一个数字，至少包含一个字母。
 *
 * @author legendjw
 */
@Value
public class AtLeastCharacterRule implements PasswordRule {
    String characters;
    int minCount;
    String errorMessage;

    public AtLeastCharacterRule(@NonNull String characters) {
        this(characters, "");
    }

    public AtLeastCharacterRule(@NonNull String characters, @NonNull String errorMessage) {
        this.characters = characters;
        this.minCount = 1;
        this.errorMessage = errorMessage;
    }

    public AtLeastCharacterRule(@NonNull String characters, int minCount) {
        this(characters, minCount, "");
    }

    public AtLeastCharacterRule(@NonNull String characters, int minCount, @NonNull String errorMessage) {
        this.characters = characters;
        this.minCount = minCount;
        this.errorMessage = errorMessage;
    }

    public AtLeastCharacterRule(@NonNull Characters characters) {
        this(characters, "");
    }

    public AtLeastCharacterRule(@NonNull Characters characters, @NonNull String errorMessage) {
        this.characters = characters.getCharacters();
        this.minCount = 1;
        this.errorMessage = errorMessage;
    }

    public AtLeastCharacterRule(@NonNull Characters characters, int minCount) {
        this(characters, minCount, "");
    }

    public AtLeastCharacterRule(@NonNull Characters characters, int minCount, @NonNull String errorMessage) {
        this.characters = characters.getCharacters();
        this.minCount = minCount;
        this.errorMessage = errorMessage;
    }

    @Override
    public ValidationResult validate(@NonNull String plainPassword) {
        int count = (int) plainPassword.chars().filter(c -> characters.indexOf(c) != -1).count();
        if (count < minCount) {
            return ValidationResult.invalid(getErrorMessage());
        }
        return ValidationResult.valid();
    }
}
