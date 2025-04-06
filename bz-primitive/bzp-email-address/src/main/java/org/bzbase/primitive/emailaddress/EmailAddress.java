package org.bzbase.primitive.emailaddress;

import com.sanctionco.jmail.EmailValidator;
import com.sanctionco.jmail.JMail;
import lombok.NonNull;
import lombok.Value;
import org.bzbase.library.ddd.type.ValueObject;

/**
 * 电子邮箱地址值对象，用于验证和表示电子邮件地址
 *
 * <p>符合标准RFC定义的邮箱地址格式的验证是复杂的，此类默认使用 <a href="https://github.com/RohanNagar/jmail">jmail</a>
 * 库来执行邮箱的验证。如果需要调邮箱地址的验证规则，可以参见此库的文档。
 *
 * @author legendjw
 */
@Value
public class EmailAddress implements ValueObject {
    /**
     * 内部使用的电子邮箱地址验证器
     */
    private static EmailValidator emailValidator = JMail.strictValidator().disallowQuotedIdentifiers();

    /**
     * 邮箱地址
     */
    String address;

    private EmailAddress(String address) {
        this.address = address;
    }

    /**
     * 创建一个邮箱地址对象并验证其格式
     *
     * @param emailAddress 待验证的电子邮件地址字符串，不能为空
     * @return 返回一个新的EmailAddress对象
     * @throws InvalidEmailAddressException 如果输入的电子邮件地址格式无效，则抛出此异常
     */
    public static EmailAddress of(@NonNull String emailAddress) throws InvalidEmailAddressException {
        emailAddress = emailAddress.trim();

        if (emailValidator.isInvalid(emailAddress)) {
            throw InvalidEmailAddressException.of(emailAddress);
        }

        return new EmailAddress(emailAddress);
    }

    /**
     * 获取电子邮件地址的本地部分（通常是用户名）
     *
     * @return 电子邮件地址的本地部分
     */
    public String getLocalPart() {
        return address.substring(0, address.indexOf('@'));
    }

    /**
     * 获取电子邮件地址中的域名部分
     *
     * @return 电子邮箱地址的域名部分
     */
    public String getDomain() {
        return address.substring(address.indexOf('@') + 1);
    }

    @Override
    public String toString() {
        return address;
    }
}
