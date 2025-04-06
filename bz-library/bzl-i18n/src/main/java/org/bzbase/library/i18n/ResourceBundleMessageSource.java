package org.bzbase.library.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 基于ResourceBundle的消息接口的实现
 *
 * @author legendjw
 */
public class ResourceBundleMessageSource implements MessageSource {
    private final String baseName;
    private final Locale locale;
    private final ResourceBundle resourceBundle;

    public ResourceBundleMessageSource(String baseName) {
        Locale locale = Locale.getDefault();
        this.resourceBundle = ResourceBundle.getBundle(baseName, locale);
        this.baseName = baseName;
        this.locale = locale;
    }

    public ResourceBundleMessageSource(String baseName, ClassLoader loader) {
        Locale locale = Locale.getDefault();
        this.resourceBundle = ResourceBundle.getBundle(baseName, locale, loader);
        this.baseName = baseName;
        this.locale = locale;
    }

    public ResourceBundleMessageSource(String baseName, Locale locale) {
        this.resourceBundle = ResourceBundle.getBundle(baseName, locale);
        this.baseName = baseName;
        this.locale = locale;
    }

    @Override
    public String getMessage(String key) {
        return resourceBundle.getString(key);
    }

    @Override
    public String getMessage(String key, Locale locale) {
        return ResourceBundle.getBundle(baseName, locale).getString(key);
    }

    @Override
    public String getMessage(String key, Object[] args) {
        String pattern = getMessage(key);
        MessageFormat messageFormat = new MessageFormat(pattern, locale);
        return messageFormat.format(args);
    }

    @Override
    public String getMessage(String key, Object[] args, Locale locale) {
        String pattern = getMessage(key, locale);
        MessageFormat messageFormat = new MessageFormat(pattern, locale);
        return messageFormat.format(args);
    }
}
