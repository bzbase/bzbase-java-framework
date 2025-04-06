package org.bzbase.primitive.phonenumber;

import com.google.i18n.phonenumbers.Phonenumber;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;
import org.bzbase.primitive.phonenumber.internal.AbstractPhoneNumber;
import org.bzbase.primitive.phonenumber.internal.PhoneNumberUtils;

/**
 * 电话号码值对象，用于表示一个有效的电话号码，包含原始号码字符串、解析后的号码对象、号码类型、运营商、地区等属性。
 *
 * <p>注意电话号码是一个宽泛的概念，可以是一个固定电话号码、移动电话号码或者VOIP电话号码等，如果需要表示验证特定类型的电话号码比如固定电话号码、
 * 移动电话号码，请使用 {@link FixedLineNumber} 和 {@link MobileNumber} 类。
 *
 * @author legendjw
 */
@Value
@EqualsAndHashCode(callSuper = true)
public class PhoneNumber extends AbstractPhoneNumber {
    /**
     * 电话号码类型
     */
    PhoneNumberType type;

    private PhoneNumber(Phonenumber.PhoneNumber parsedPhoneNumber) {
        super(parsedPhoneNumber);
        type = PhoneNumberUtils.getPhoneNumberType(parsedNumber);
    }

    /**
     * 创建一个电话号码对象并验证其有效性，此方法使用宽松的验证规则，仅验证号码的长度是否符合，如果需要更严格的验证，
     * 请使用 {@link #ofStrict(String)} 方法。
     *
     * <p>输入的电话号码支持标准的国际电话号码格式以及国内电话号码格式，比如以下格式都是合法的：
     * <ul>
     *     <li>+8618712345678</li>
     *     <li>+86 187 1234 5678</li>
     *     <li>18712345678</li>
     *     <li>187 1234 5678</li>
     *     <li>01023456789</li>
     *     <li>010-2345-6789</li>
     * </ul>
     *
     * @param phoneNumber 电话号码字符串，不能为空
     * @return PhoneNumber对象
     * @throws InvalidPhoneNumberException 如果输入的电话号码格式不正确，则抛出此异常
     */
    public static PhoneNumber of(@NonNull String phoneNumber) throws InvalidPhoneNumberException {
        // 解析输入的电话号码
        Phonenumber.PhoneNumber parsePhoneNumber = PhoneNumberUtils.parsePhoneNumber(phoneNumber);

        // 验证解析后的电话号码是否可能有效
        if (!PhoneNumberUtils.isPossibleNumber(parsePhoneNumber)) {
            throw new InvalidPhoneNumberException(phoneNumber);
        }

        return new PhoneNumber(parsePhoneNumber);
    }

    /**
     * 创建一个指定类型的电话号码对象
     *
     * @param phoneNumber 电话号码字符串
     * @param phoneNumberType 期望的电话号码类型
     * @return PhoneNumber对象
     * @throws InvalidPhoneNumberException 如果电话号码无效或类型不匹配
     * @see #of(String)
     */
    public static PhoneNumber of(@NonNull String phoneNumber, @NonNull PhoneNumberType phoneNumberType) throws InvalidPhoneNumberException {
        // 解析输入的电话号码
        Phonenumber.PhoneNumber parsePhoneNumber;
        try {
            parsePhoneNumber = PhoneNumberUtils.parsePhoneNumber(phoneNumber);
        }
        catch (InvalidPhoneNumberException e) {
            throw new InvalidPhoneNumberException(phoneNumber, phoneNumberType);
        }

        // 验证解析后的电话号码是否可能有效
        if (!PhoneNumberUtils.isPossibleNumberForType(parsePhoneNumber, phoneNumberType)) {
            throw new InvalidPhoneNumberException(phoneNumber, phoneNumberType);
        }

        return new PhoneNumber(parsePhoneNumber);
    }

    /**
     * 创建一个电话号码对象并严格验证其有效性，包括检查长度、号码段是否符合指定国家运营商已发布的号码规则。
     *
     * <p>如果采用此方法需要注意，本库依赖于 <a href="https://github.com/google/libphonenumber">Google libphonenumber</a> 进行
     * 号码的严格有效性验证，如果运营商发布了新的号码段则需要更新此库的依赖以解析最新的号码规则，否则有可能会误判新的号码为无效号码。
     *
     * <p>输入的电话号码支持的格式参见 {@link #of(String)} 方法。
     *
     * @param phoneNumber 电话号码字符串，不能为空
     * @return PhoneNumber对象
     * @throws InvalidPhoneNumberException 如果输入的电话号码格式不正确，则抛出此异常
     */
    public static PhoneNumber ofStrict(@NonNull String phoneNumber) throws InvalidPhoneNumberException {
        // 解析输入的电话号码字符串
        Phonenumber.PhoneNumber parsePhoneNumber = PhoneNumberUtils.parsePhoneNumber(phoneNumber);

        // 验证解析后的电话号码是否有效
        if (!PhoneNumberUtils.isValidNumber(parsePhoneNumber)) {
            throw new InvalidPhoneNumberException(phoneNumber);
        }

        return new PhoneNumber(parsePhoneNumber);
    }

    /**
     * 创建一个指定类型的电话号码对象
     *
     * @param phoneNumber 电话号码字符串
     * @param phoneNumberType 期望的电话号码类型
     * @return PhoneNumber对象
     * @throws InvalidPhoneNumberException 如果电话号码无效或类型不匹配
     * @see #ofStrict(String)
     */
    public static PhoneNumber ofStrict(@NonNull String phoneNumber, @NonNull PhoneNumberType phoneNumberType) throws InvalidPhoneNumberException {
        // 创建电话号码对象如果号码无效则转换为包含期望号码类型的异常进行抛出
        PhoneNumber phoneNumberObject;
        try {
            phoneNumberObject = ofStrict(phoneNumber);
        }
        catch (InvalidPhoneNumberException e) {
            throw new InvalidPhoneNumberException(phoneNumber, phoneNumberType);
        }
        // 验证号码是否是指定电话号码类型
        if (phoneNumberObject.getType() != phoneNumberType) {
            throw new InvalidPhoneNumberException(phoneNumber, phoneNumberType);
        }
        return phoneNumberObject;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
