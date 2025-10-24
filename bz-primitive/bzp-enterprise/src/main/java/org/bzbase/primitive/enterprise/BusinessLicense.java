package org.bzbase.primitive.enterprise;

import lombok.Value;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.ValueObject;

import java.net.URL;

/**
 * 营业执照值对象，仅表示营业执照的照片副本
 */
@Value
public class BusinessLicense implements ValueObject {
    /**
     * 营业执照照片链接
     */
    URL licensePhotoUrl;

    /**
     * 使用给定链接创建营业执照值对象
     *
     * @param licensePhotoUrl 营业执照照片链接
     */
    private BusinessLicense(URL licensePhotoUrl) {
        this.licensePhotoUrl = licensePhotoUrl;
    }

    /**
     * 创建营业执照值对象
     *
     * @param licensePhotoUrl 营业执照照片链接
     * @return 营业执照值对象
     */
    public static BusinessLicense of(URL licensePhotoUrl) {
        if (licensePhotoUrl == null) {
            throw new DomainException("营业执照照片URL不能为空");
        }
        return new BusinessLicense(licensePhotoUrl);
    }
}
