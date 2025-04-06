package org.bzbase.primitive.user;

import java.net.MalformedURLException;
import java.net.URL;

import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.ValueObject;

import lombok.NonNull;
import lombok.Value;

/**
 * 用户头像
 *
 * @author legendjw
 */
@Value
public class Picture implements ValueObject {
    /**
     * 头像访问地址
     */
    URL url;

    /**
     * 私有构造函数，用于创建Picture对象
     *
     * @param url URL
     */
    private Picture(URL url) {
        this.url = url;
    }

    /**
     * 根据URL创建一个头像
     *
     * @param url URL
     * @return 返回一个新的Picture对象
     */
    public static Picture of(@NonNull URL url) {
        return new Picture(url);
    }

    /**
     * 根据URL字符串创建一个头像
     *
     * @param url URL字符串
     * @return 返回一个新的Picture对象
     */
    public static Picture of(@NonNull String url) {
        try {
            return new Picture(new URL(url));
        } catch (MalformedURLException e) {
            throw new DomainException("Invalid URL: " + url, e);
        }
    }

    @Override
    public String toString() {
        return url.toString();
    }
}
