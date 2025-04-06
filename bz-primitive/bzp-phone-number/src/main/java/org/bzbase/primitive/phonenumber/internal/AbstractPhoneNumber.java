package org.bzbase.primitive.phonenumber.internal;

import com.google.i18n.phonenumbers.Phonenumber;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.bzbase.library.ddd.type.ValueObject;

/**
 * 抽象电话号码，提供电话号码的通用字段以及方法。
 *
 * @author legendjw
 */
@Getter
@EqualsAndHashCode
public abstract class AbstractPhoneNumber implements ValueObject {
    /**
     * 电话号码的值，如果解析的电话号码是国际号码则为电话号码的E164国际号码格式，否则为国内号码格式
     */
    protected final String number;

    /**
     * 解析后供内部后续使用的电话号码对象，属于内部实现细节不对外开放
     */
    protected final Phonenumber.PhoneNumber parsedNumber;

    /**
     * 私有化的构造函数，不允许外部直接创建
     *
     * @param parsedNumber 解析后的电话号码对象
     */
    protected AbstractPhoneNumber(Phonenumber.PhoneNumber parsedNumber) {
        this.parsedNumber = parsedNumber;
        this.number = PhoneNumberUtils.isRawNumberContainsCountryCode(parsedNumber) ? toE164Format() : toNationalFormat();
    }

    /**
     * 将电话号码转换为E.164格式，比如<b>+8618795951234</b>，数据库存储国际号码时建议使用此格式
     *
     * @return 返回E.164电话号码字符串
     */
    public String toE164Format() {
        return PhoneNumberUtils.toE164Format(parsedNumber);
    }

    /**
     * 将电话号码转换为E.164格式的可读形式，使用空格分隔符，比如<b>+86 187 9595 1234</b>，在前台展示给用户国际号码时建议使用此格式
     *
     * @return 返回格式化后的E.164电话号码字符串
     */
    public String toReadableE164Format() {
        return PhoneNumberUtils.toReadableE164Format(parsedNumber);
    }

    /**
     * 将电话号码转换为国内格式，不包含国家代码，比如<b>18795951234</b>，数据库存储国内号码时建议使用此格式
     *
     * @return 返回国内电话号码字符串
     */
    public String toNationalFormat() {
        return PhoneNumberUtils.toNationalFormat(parsedNumber);
    }

    /**
     * 将电话号码转换为国内格式的可读形式，使用空格分隔符，比如<b>187 9595 1234</b>，在前台展示给用户国内号码时建议使用此格式
     *
     * @return 返回格式化后的国内电话号码字符串
     */
    public String toReadableNationalFormat() {
        return PhoneNumberUtils.toReadableNationalFormat(parsedNumber);
    }

    /**
     * 将电话号码转换为RFC3966格式，比如<b>tel:+86-187-9595-1234</b>，在网页中链接到此格式，用户点击链接即可直接拨打电话。
     *
     * @return 返回RFC3966格式的电话号码字符串
     */
    public String toRFC3966Format() {
        return PhoneNumberUtils.toRFC3966Format(parsedNumber);
    }

    /**
     * 获取电话号码的归属地，比如<b>江苏省南京市</b>
     *
     * @return 返回电话号码可能所在的归属地，如果无法确定位置，则返回空字符串
     */
    public String getLocation() {
        return PhoneNumberUtils.getLocation(parsedNumber);
    }

    /**
     * 获取电话号码的运营商名称，比如<b>中国移动</b>
     *
     * @return 返回电话号码所属的运营商名称，如果没有，则返回空字符串
     */
    public String getCarrier() {
        return PhoneNumberUtils.getCarrier(parsedNumber);
    }

    @Override
    public String toString() {
        return this.number;
    }
}
