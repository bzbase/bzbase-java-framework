package org.bzbase.primitive.phonenumber;

import java.util.MissingResourceException;

import org.bzbase.library.ddd.exception.DomainException;

import lombok.Getter;

/**
 * 无效的电话号码异常
 *
 * @author legendjw
 */
@Getter
public class InvalidPhoneNumberException extends DomainException {
    private static final long serialVersionUID = 1L;

    /**
     * 原始输入的无效的电话号码
     */
    private final String phoneNumber;

    /**
     * 电话号码类型
     */
    private final PhoneNumberType phoneNumberType;

    /**
     * 构造函数
     *
     * @param phoneNumber 无效的电话号码
     */
    public InvalidPhoneNumberException(String phoneNumber) {
        super(createInvalidPhoneNumberMessage(phoneNumber));
        this.phoneNumber = phoneNumber;
        this.phoneNumberType = PhoneNumberType.UNKNOWN;
    }

    /**
     * 构造函数
     *
     * @param phoneNumber 无效的电话号码
     * @param phoneNumberType 电话号码类型
     */
    public InvalidPhoneNumberException(String phoneNumber, PhoneNumberType phoneNumberType) {
        super(createInvalidPhoneNumberMessage(phoneNumber, phoneNumberType));
        this.phoneNumber = phoneNumber;
        this.phoneNumberType = phoneNumberType;
    }

    /**
     * 构造函数
     *
     * @param phoneNumber 无效的电话号码
     * @param cause 底层异常
     */
    public InvalidPhoneNumberException(String phoneNumber, Throwable cause) {
        super(createInvalidPhoneNumberMessage(phoneNumber), cause);
        this.phoneNumber = phoneNumber;
        this.phoneNumberType = PhoneNumberType.UNKNOWN;
    }

    /**
     * 构造函数
     *
     * @param phoneNumber 无效的电话号码
     * @param phoneNumberType 电话号码类型
     * @param cause 底层异常
     */
    public InvalidPhoneNumberException(String phoneNumber, PhoneNumberType phoneNumberType, Throwable cause) {
        super(createInvalidPhoneNumberMessage(phoneNumber, phoneNumberType), cause);
        this.phoneNumber = phoneNumber;
        this.phoneNumberType = phoneNumberType;
    }

    /**
     * 生成无效的手机号码异常消息
     *
     * @param phoneNumber 引发异常的电话号码
     * @return 返回一个包含无效手机号码详情的异常消息
     */
    private static String createInvalidPhoneNumberMessage(String phoneNumber) {
        return Module.getMessageSource().getMessage("PhoneNumber.invalidPhoneNumber", new Object[] { phoneNumber });
    }

    /**
     * 生成无效的手机号码异常消息
     *
     * @param phoneNumber 引发异常的电话号码
     * @param phoneNumberType 电话号码类型
     * @return 返回一个包含无效手机号码详情的异常消息
     */
    private static String createInvalidPhoneNumberMessage(String phoneNumber, PhoneNumberType phoneNumberType) {
        try {
            String phoneNumberTypeKey = String.format("PhoneNumberType.%s", phoneNumberType.name());
            String phoneNumberTypeMessage = Module.getMessageSource().getMessage(phoneNumberTypeKey);
            return Module.getMessageSource().getMessage("PhoneNumber.invalidPhoneNumberWithType",
                    new Object[] {phoneNumberTypeMessage , phoneNumber });
        }
        catch (MissingResourceException e) {
            return createInvalidPhoneNumberMessage(phoneNumber);
        }
    }
}
