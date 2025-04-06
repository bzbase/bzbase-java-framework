package org.bzbase.domain.user.valueobject;

import lombok.Value;

import java.time.Instant;

/**
 * 可验证的
 *
 * @author legendjw
 */
@Value
public class Verifiable<T> {
    /**
     * 需要验证的值
     */
    T value;

    /**
     * 是否已验证
     */
    boolean verified;

    /**
     * 验证时间
     */
    Instant verifiedAt;

    /**
     * 返回一个已验证的对象
     *
     * @param t 需要验证的值
     * @return 已验证的对象
     * @param <T> 需要验证的值
     */
    public static <T> Verifiable<T> verified(T t) {
        return new Verifiable<>(t, true, Instant.now());
    }

    /**
     * 返回一个未验证的对象
     *
     * @param t 需要验证的值
     * @return 未验证的对象
     * @param <T> 需要验证的值
     */
    public static <T> Verifiable<T> unverified(T t) {
        return new Verifiable<>(t, false, null);
    }

    /**
     * 根据已有的可验证的值实例化一个新的对象
     *
     * @param t 需要验证的值
     * @param verifiable 可验证的值
     * @return 新的可验证的对象
     * @param <T> 需要验证的值
     */
    public static <T> Verifiable<T> from(T t, Verifiable<?> verifiable) {
        return new Verifiable<>(t, verifiable.isVerified(), verifiable.getVerifiedAt());
    }
}
