package org.bzbase.primitive.phonenumber;

import com.google.i18n.phonenumbers.Phonenumber;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;
import org.bzbase.primitive.phonenumber.internal.AbstractPhoneNumber;

/**
 * 手机号码值对象，用于表示一个有效的手机号码。
 *
 * @author legendjw
 */
@Value
@EqualsAndHashCode(callSuper = true)
public class MobileNumber extends AbstractPhoneNumber {
    private MobileNumber(Phonenumber.PhoneNumber parsedPhoneNumber) {
        super(parsedPhoneNumber);
    }

    /**
     * 创建一个手机号码对象并验证其有效性，此方法使用宽松的验证规则，仅验证号码的长度是否符合，如果需要更严格的验证，
     * 请使用 {@link #ofStrict(String)} 方法。
     *
     * <p>输入的手机号码支持标准的国际手机号码格式以及国内手机号码格式，比如以下格式都是合法的：
     *
     * <ul>
     *     <li>+8618712345678</li>
     *     <li>+86 187 1234 5678</li>
     *     <li>18712345678</li>
     *     <li>187 1234 5678</li>
     * </ul>
     *
     * @param phoneNumber 手机号码字符串，不能为空
     * @return MobileNumber对象
     * @throws InvalidPhoneNumberException 如果输入的手机号码格式不正确，则抛出此异常
     */
    public static MobileNumber of(@NonNull String phoneNumber) throws InvalidPhoneNumberException {
        return new MobileNumber(PhoneNumber.of(phoneNumber, PhoneNumberType.MOBILE).getParsedNumber());
    }

    /**
     * 创建一个手机号码对象并严格验证其有效性，包括检查长度、号码段是否符合指定国家运营商已发布的号码规则。
     *
     * <p>如果采用此方法需要注意，本库依赖于 <a href="https://github.com/google/libphonenumber">Google libphonenumber</a> 进行
     * 号码的严格有效性验证，如果运营商发布了新的号码段则需要更新此库的依赖以解析最新的号码规则，否则有可能会误判新的号码为无效号码。
     *
     * <p>输入的手机号码支持的格式参见 {@link #of(String)} 方法。
     *
     * @param phoneNumber 手机号码字符串，不能为空
     * @return MobileNumber对象
     * @throws InvalidPhoneNumberException 如果输入的手机号码格式不正确，则抛出此异常
     */
    public static MobileNumber ofStrict(@NonNull String phoneNumber) throws InvalidPhoneNumberException {
        return new MobileNumber(PhoneNumber.ofStrict(phoneNumber, PhoneNumberType.MOBILE).getParsedNumber());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
