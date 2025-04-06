package org.bzbase.primitive.phonenumber;

/**
 * 电话号码类型
 *
 * @author legendjw
 */
public enum PhoneNumberType {
    // 固定电话号码，用于家庭或办公室的有线电话网络
    FIXED_LINE,
    // 移动电话号码(手机号码)，由移动运营商提供，适用于手机等移动通信设备
    MOBILE,
    // 固定电话号码或者移动电话号码，有些国家的号码无法区分具体是哪个
    FIXED_LINE_OR_MOBILE,
    // VOIP号码，通过互联网协议（IP）进行语音传输的网络电话号码
    VOIP,
    // 免费电话号码（如800、400号码）
    TOLL_FREE,
    // 高费率电话号码，指对某些服务（包括信息和娱乐）向拨打者收取更高费率的电话号码
    PREMIUM_RATE,
    // 共享成本电话号码，费用由主叫方和被叫方共同分担的主被叫分摊业务号码（比如400电话）
    SHARED_COST,
    // 个人虚拟号码，一种不直接关联电话线的电话号码
    PERSONAL_NUMBER,
    // 未知
    UNKNOWN
}
