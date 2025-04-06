package org.bzbase.primitive.emailaddress;


import org.bzbase.library.ddd.exception.DomainException;

import lombok.Getter;

/**
 * 无效的邮箱地址异常
 *
 * @author legendjw
 */
@Getter
public class InvalidEmailAddressException extends DomainException {
    private static final long serialVersionUID = 1L;

    /**
     * 原始输入的无效的邮箱地址
     */
    private final String emailAddress;

    public InvalidEmailAddressException(String message) {
        super(message);
        this.emailAddress = "";
    }

    public InvalidEmailAddressException(String message, Throwable cause) {
        super(message, cause);
        this.emailAddress = "";
    }

    public InvalidEmailAddressException(String message, String emailAddress) {
        super(message);
        this.emailAddress = emailAddress;
    }

    public InvalidEmailAddressException(String message, String emailAddress, Throwable cause) {
        super(message, cause);
        this.emailAddress = emailAddress;
    }

    /**
     * 创建一个InvalidEmailAddressException对象，用于表示无效的电子邮件地址
     *
     * @param emailAddress 导致异常的无效电子邮件地址字符串
     * @return InvalidEmailAddressException的实例，包含有关无效电子邮件地址的详细信息
     */
    public static InvalidEmailAddressException of(String emailAddress) {
        return new InvalidEmailAddressException(createInvalidEmailAddressMessage(emailAddress), emailAddress);
    }

    /**
     * 创建一个InvalidEmailAddressException对象，用于表示无效的电子邮件地址
     *
     * @param emailAddress 导致异常的无效电子邮件地址
     * @param cause 异常的原因，通常是一个下层异常
     * @return InvalidEmailAddressException的实例，包含有关无效电子邮件地址的详细信息
     */
    public static InvalidEmailAddressException of(String emailAddress, Throwable cause) {
        return new InvalidEmailAddressException(createInvalidEmailAddressMessage(emailAddress), emailAddress, cause);
    }

    /**
     * 生成无效的邮箱地址异常消息
     *
     * @param emailAddress 引发异常的邮箱地址
     * @return 返回一个包含无效邮箱地址详情的异常消息
     */
    private static String createInvalidEmailAddressMessage(String emailAddress) {
        return Module.getMessageSource().getMessage("EmailAddress.invalidEmailAddress", new Object[] { emailAddress });
    }
}
