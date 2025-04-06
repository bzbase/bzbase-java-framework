package org.bzbase.library.i18n;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

class ResourceBundleMessageSourceTest {
    @Test
    void getMessage() {
        MessageSource messageSource = new ResourceBundleMessageSource(String.format("%s.messages", this.getClass().getPackage().getName()));
        assertThat(messageSource.getMessage("message", Locale.US)).isEqualTo("test");
        assertThat(messageSource.getMessage("message", Locale.SIMPLIFIED_CHINESE)).isEqualTo("测试");
        assertThat(messageSource.getMessage("messageWithArgs", new Object[]{1, "hello"}, Locale.US)).isEqualTo("test 1 hello");
        assertThat(messageSource.getMessage("messageWithArgs", new Object[]{1, "hello"}, Locale.SIMPLIFIED_CHINESE)).isEqualTo("测试 1 hello");
    }
}
