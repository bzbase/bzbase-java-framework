package org.bzbase.primitive.password.rule;

import lombok.NonNull;
import lombok.Value;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 仅允许包含指定字符的规则，使用场景比如只允许包含数字或者字母字符。
 *
 * @author legendjw
 */
@Value
public class OnlyAllowedCharacterRule implements PasswordRule {
    String allowedCharacters;
    String errorMessage;

    public OnlyAllowedCharacterRule(@NonNull String allowedCharacters) {
        this(allowedCharacters, "");
    }

    public OnlyAllowedCharacterRule(@NonNull String allowedCharacters, @NonNull String errorMessage) {
        this.allowedCharacters = allowedCharacters;
        this.errorMessage = errorMessage;
    }

    public OnlyAllowedCharacterRule(@NonNull Characters characters) {
        this(characters, "");
    }

    public OnlyAllowedCharacterRule(@NonNull Characters characters, @NonNull String errorMessage) {
        this.allowedCharacters = characters.getCharacters();
        this.errorMessage = errorMessage;
    }


    public OnlyAllowedCharacterRule(@NonNull Set<Characters> characters) {
        this(characters, "");
    }

    public OnlyAllowedCharacterRule(@NonNull Set<Characters> characters, @NonNull String errorMessage) {
        if (characters.isEmpty()) {
            throw new IllegalArgumentException("characters can not be empty");
        }
        this.allowedCharacters = characters.stream().map(Characters::getCharacters).collect(Collectors.joining());
        this.errorMessage = errorMessage;
    }

    @Override
    public ValidationResult validate(@NonNull String plainPassword) {
        if (plainPassword.chars().anyMatch(c -> allowedCharacters.indexOf(c) == -1)) {
            return ValidationResult.invalid(getErrorMessage());
        }
        return ValidationResult.valid();
    }
}
