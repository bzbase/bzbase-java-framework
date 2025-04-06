package org.bzbase.primitive.phonenumber;

import lombok.Getter;
import org.bzbase.library.i18n.MessageSource;
import org.bzbase.library.i18n.ResourceBundleMessageSource;

/**
 * 模块类
 */
public class Module {
    @Getter
    private static final String moduleId = "PhoneNumber";
    @Getter
    private static final String packageName = Module.class.getPackage().getName();
    @Getter
    private static final MessageSource messageSource =
            new ResourceBundleMessageSource(String.format("%s.messages", packageName), Module.class.getClassLoader());
}
