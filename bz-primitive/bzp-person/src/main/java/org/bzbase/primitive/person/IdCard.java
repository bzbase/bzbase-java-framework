package org.bzbase.primitive.person;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.ValueObject;

import lombok.Value;

/**
 * 身份证值对象
 */	
@Value
public class IdCard implements ValueObject {
	/**
	 * 身份证号
	 */
	IdNumber idNumber;

	/**
	 * 身份证正面照片 URL
	 */
    URL frontPhotoUrl;
    
    /**
     * 身份证背面照片 URL
     */
    URL backPhotoUrl;

	private IdCard(IdNumber idNumber, URL frontPhotoUrl, URL backPhotoUrl) {
		this.idNumber = idNumber;
		this.frontPhotoUrl = frontPhotoUrl;
		this.backPhotoUrl = backPhotoUrl;
	}

	/**
	 * 创建身份证值对象
	 *
	 * @param idNumber 身份证号
	 * @param frontPhoto 身份证正面照片 URL
	 * @param backPhoto 身份证背面照片 URL
	 * @return 返回一个新的身份证对象
	 */
    public static IdCard of(IdNumber idNumber, String frontPhoto, String backPhoto) {
        try {
			URL frontPhotoUrl = StringUtils.isNotBlank(frontPhoto) ? new URL(frontPhoto) : null;
			URL backPhotoUrl = StringUtils.isNotBlank(backPhoto) ? new URL(backPhoto) : null;
            return new IdCard(idNumber, frontPhotoUrl, backPhotoUrl);
        } catch (MalformedURLException e) {
            throw new DomainException("无效的照片 URL 格式", e);
        }
    }
}
