package org.bzbase.primitive.phonenumber.internal;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberToCarrierMapper;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;
import org.bzbase.primitive.phonenumber.InvalidPhoneNumberException;
import org.bzbase.primitive.phonenumber.PhoneNumberType;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 电话号码工具类
 * <p>
 * 主要业务逻辑调用Google libphonenumber，其详细用法请访问 <a href="https://github.com/google/libphonenumber">官方地址</a>进行查看。
 *
 * @author legendjw
 */
public class PhoneNumberUtils {
    public static final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
    public static final PhoneNumberOfflineGeocoder phoneNumberOfflineGeocoder = PhoneNumberOfflineGeocoder.getInstance();
    public static final PhoneNumberToCarrierMapper phoneNumberToCarrierMapper = PhoneNumberToCarrierMapper.getInstance();

    /**
     * 解析电话号码字符串为Google PhoneNumber对象
     *
     * @param phoneNumber 电话号码字符串
     * @return 解析后的Google PhoneNumber对象
     * @throws InvalidPhoneNumberException 如果电话号码格式不正确
     */
    public static Phonenumber.PhoneNumber parsePhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
        // 验证电话号码字符串是否仅包含允许的字符，允许的字符包括数字、空格、加号、括号和中横线
        boolean containsOnlyAllowedCharacters = phoneNumber.matches("[0-9()+\\-\\s]+");
        if (!containsOnlyAllowedCharacters) {
            throw new InvalidPhoneNumberException(phoneNumber);
        }
        try {
            // 使用Google的libphonenumber库解析电话号码，并保留原始输入
            return phoneNumberUtil.parseAndKeepRawInput(phoneNumber, Locale.getDefault().getCountry());
        } catch (NumberParseException e) {
            throw new InvalidPhoneNumberException(phoneNumber, e);
        }
    }

    /**
     * 检查给定的电话号码是否可能是有效的，验证宽松仅仅验证号码的长度
     *
     * @param phoneNumber 电话号码对象
     * @return 如果电话号码可能有效，则返回true；否则返回false
     */
    public static boolean isPossibleNumber(Phonenumber.PhoneNumber phoneNumber) {
        return phoneNumberUtil.isPossibleNumber(phoneNumber);
    }

    /**
     * 检查给定的电话号码是否可能是有效的并且是指定的电话号码类型，验证宽松仅仅验证号码的长度
     *
     * @param phoneNumber 电话号码对象
     * @param type 电话号码类型，例如固定电话、移动电话等
     * @return 如果电话号码可能有效且是给定类型，则返回true；否则返回false
     */
    public static boolean isPossibleNumberForType(Phonenumber.PhoneNumber phoneNumber, PhoneNumberType type) {
        PhoneNumberUtil.PhoneNumberType phoneNumberType;
        try {
            phoneNumberType = PhoneNumberUtil.PhoneNumberType.valueOf(type.name());
        }
        catch (IllegalArgumentException e) {
            return false;
        }
        return phoneNumberUtil.isPossibleNumberForType(phoneNumber, phoneNumberType);
    }

    /**
     * 检查给定的电话号码是否有效，严格验证号码是否符合指定国家要求的长度以及号码段
     *
     * @param phoneNumber 电话号码对象
     * @return 如果电话号码有效则为true，否则为false
     */
    public static boolean isValidNumber(Phonenumber.PhoneNumber phoneNumber) {
        return phoneNumberUtil.isValidNumber(phoneNumber);
    }

    /**
     * 判断电话号码的原始格式中是否包含国家代码
     *
     * @param phoneNumber 电话号码对象
     * @return 如果电话号码的原始格式中包含国家代码，则返回true；否则返回false
     */
    public static boolean isRawNumberContainsCountryCode(Phonenumber.PhoneNumber phoneNumber) {
        // 检查电话号码对象是否具有国家代码来源信息
        if (!phoneNumber.hasCountryCodeSource()) {
            return false;
        }

        // 定义哪些国家代码来源被视为包含国家代码
        Set<Phonenumber.PhoneNumber.CountryCodeSource> fromNumberSources = Stream.of(
                        Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITH_PLUS_SIGN,
                        Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITH_IDD,
                        Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITHOUT_PLUS_SIGN)
                .collect(Collectors.toSet());

        // 检查电话号码的国家代码来源是否在定义的来源列表中
        return fromNumberSources.contains(phoneNumber.getCountryCodeSource());
    }

    /**
     * 将电话号码转换为E.164格式
     *
     * @param phoneNumber 电话号码对象
     * @return 返回E.164电话号码字符串
     */
    public static String toE164Format(Phonenumber.PhoneNumber phoneNumber) {
        return phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164);
    }

    /**
     * 将电话号码转换为E.164格式的可读形式，使用空格分隔符
     *
     * @param phoneNumber 电话号码对象
     * @return 返回格式化后的E.164电话号码字符串
     */
    public static String toReadableE164Format(Phonenumber.PhoneNumber phoneNumber) {
        return phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
    }

    /**
     * 将电话号码转换为国内格式，不包含国家代码
     *
     * @param phoneNumber 电话号码对象
     * @return 返回国内电话号码字符串
     */
    public static String toNationalFormat(Phonenumber.PhoneNumber phoneNumber) {
        return phoneNumberUtil.getNationalSignificantNumber(phoneNumber);
    }

    /**
     * 将电话号码转换为国内格式的可读形式，使用空格分隔符
     *
     * @param phoneNumber 电话号码对象
     * @return 返回格式化后的国内电话号码字符串
     */
    public static String toReadableNationalFormat(Phonenumber.PhoneNumber phoneNumber) {
        return phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.NATIONAL);
    }

    /**
     * 将电话号码转换为RFC3966格式
     *
     * @param phoneNumber 电话号码对象
     * @return 返回RFC3966格式的电话号码字符串
     */
    public static String toRFC3966Format(Phonenumber.PhoneNumber phoneNumber) {
        return phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.RFC3966);
    }

    /**
     * 获取电话号码类型
     *
     * @param phoneNumber 电话号码对象
     * @return 电话号码的特定类型如果无法确定类型，则返回UNKNOWN
     */
    public static PhoneNumberType getPhoneNumberType(Phonenumber.PhoneNumber phoneNumber) {
        PhoneNumberUtil.PhoneNumberType internalNumberType = phoneNumberUtil.getNumberType(phoneNumber);

        Map<PhoneNumberUtil.PhoneNumberType, PhoneNumberType> numberTypeMap = new HashMap<>();
        numberTypeMap.put(PhoneNumberUtil.PhoneNumberType.FIXED_LINE, PhoneNumberType.FIXED_LINE);
        numberTypeMap.put(PhoneNumberUtil.PhoneNumberType.MOBILE, PhoneNumberType.MOBILE);
        numberTypeMap.put(PhoneNumberUtil.PhoneNumberType.FIXED_LINE_OR_MOBILE, PhoneNumberType.FIXED_LINE_OR_MOBILE);
        numberTypeMap.put(PhoneNumberUtil.PhoneNumberType.VOIP, PhoneNumberType.VOIP);
        numberTypeMap.put(PhoneNumberUtil.PhoneNumberType.TOLL_FREE, PhoneNumberType.TOLL_FREE);
        numberTypeMap.put(PhoneNumberUtil.PhoneNumberType.PREMIUM_RATE, PhoneNumberType.PREMIUM_RATE);
        numberTypeMap.put(PhoneNumberUtil.PhoneNumberType.SHARED_COST, PhoneNumberType.SHARED_COST);
        numberTypeMap.put(PhoneNumberUtil.PhoneNumberType.PERSONAL_NUMBER, PhoneNumberType.PERSONAL_NUMBER);
        numberTypeMap.put(PhoneNumberUtil.PhoneNumberType.UNKNOWN, PhoneNumberType.UNKNOWN);

        return numberTypeMap.getOrDefault(internalNumberType, PhoneNumberType.UNKNOWN);
    }

    /**
     * 根据电话号码获取其可能的归属地
     *
     * @param phoneNumber 电话号码对象
     * @return 返回电话号码可能所在的归属地，如果无法确定位置，则返回空字符串
     */
    public static String getLocation(Phonenumber.PhoneNumber phoneNumber) {
        return phoneNumberOfflineGeocoder.getDescriptionForNumber(phoneNumber, Locale.getDefault());
    }

    /**
     * 根据电话号码获取其运营商名称
     *
     * @param phoneNumber 电话号码
     * @return 返回电话号码所属的运营商名称，如果没有，则返回空字符串
     */
    public static String getCarrier(Phonenumber.PhoneNumber phoneNumber) {
        return phoneNumberToCarrierMapper.getNameForNumber(phoneNumber, Locale.getDefault());
    }
}
