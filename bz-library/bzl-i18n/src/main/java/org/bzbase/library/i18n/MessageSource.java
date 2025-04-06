package org.bzbase.library.i18n;

import java.util.Locale;

/**
 * 解析国际化消息的接口
 *
 * @author legendjw
 */
public interface MessageSource {
    /**
     * 根据指定的消息key获取消息
     *
     * @param key 消息key
     * @return 消息文本
     */
    String getMessage(String key);

    /**
     * 根据指定的消息key以及地区设置获取消息
     *
     * @param key 消息key
     * @param locale 地区设置
     * @return 消息文本
     */
    String getMessage(String key, Locale locale);

    /**
     * 根据指定的消息key以及数组参数来获取格式化后的消息
     *
     * @param key 消息key
     * @param args 数组参数，用于替换消息中的{0},{1}占位符
     * @return 格式化后的消息
     */
    String getMessage(String key, Object[] args);

    /**
     * 根据指定的消息key以及数组参数以及地区设置来获取格式化后的消息
     *
     * @param key 消息key
     * @param args 数组参数，用于替换消息中的{0},{1}占位符
     * @param locale 地区设置
     * @return 格式化后的消息
     */
    String getMessage(String key, Object[] args, Locale locale);
}
