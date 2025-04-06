package org.bzbase.domain.organization.valueobject;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.ValueObject;

import lombok.NonNull;
import lombok.Value;

/**
 * 银行卡值对象
 */
@Value
public class BankCard implements ValueObject {
    
    /**
     * 银行名称
     */
    String bankName;
    
    /**
     * 银行卡号
     */
    String cardNumber;
    
    /**
     * 银行卡正面照片 URL
     */
    URL frontPhotoUrl;
    
    /**
     * 银行卡背面照片 URL
     */
    URL backPhotoUrl;
    
    private BankCard(String bankName, String cardNumber, URL frontPhotoUrl, URL backPhotoUrl) {
        this.bankName = bankName;
        this.cardNumber = cardNumber;
        this.frontPhotoUrl = frontPhotoUrl;
        this.backPhotoUrl = backPhotoUrl;
    }
    
    /**
     * 创建银行卡值对象
     *
     * @param bankName 银行名称
     * @param cardNumber 银行卡号
     * @param frontPictureUrl 银行卡正面照片 URL
     * @param backPictureUrl 银行卡背面照片 URL
     * @return 银行卡值对象
     */
    public static BankCard of(String bankName, String cardNumber, String frontPhoto, String backPhoto) {
		//if (StringUtils.isBlank(bankName)) {
		//	throw new DomainException("银行名称不能为空");
		//}
		//if (StringUtils.isBlank(cardNumber)) {
		//	throw new DomainException("银行卡号不能为空");
		//}
		if (!StringUtils.isBlank(cardNumber) && !cardNumber.matches("\\d{16,19}")) {
			throw new DomainException("银行卡号格式不正确，应为16-19位数字");
		}
        
        try {
			URL frontPhotoUrl = StringUtils.isNotBlank(frontPhoto) ? new URL(frontPhoto) : null;
			URL backPhotoUrl = StringUtils.isNotBlank(backPhoto) ? new URL(backPhoto) : null;	
            return new BankCard(bankName, cardNumber, frontPhotoUrl, backPhotoUrl);
        } catch (MalformedURLException e) {
            throw new DomainException("无效的照片 URL 格式", e);
        }
    }
} 