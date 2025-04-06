package org.bzbase.primitive.person;

import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.ValueObject;

import lombok.Value;

/**
 * 身份证号
 */
@Value	
public class IdNumber implements ValueObject {
	/**
	 * 身份证号
	 */
    String value;

	private IdNumber(String value) {
		this.value = value;
	}

	/**
	 * 根据身份证号创建一个身份证号对象
	 *
	 * @param idNumber 身份证号
	 * @return 返回一个新的身份证号对象
	 */
	public static IdNumber of(String idNumber) {
		if (idNumber == null || idNumber.isEmpty()) {
			throw new DomainException("身份证号不能为空");
		}
		
		// 验证身份证号格式
		if (!isValidIdNumber(idNumber)) {
			throw new DomainException("身份证号格式不正确");
		}
		
		return new IdNumber(idNumber);
	}
	
	/**
	 * 验证身份证号是否有效
	 * 
	 * @param idNumber 身份证号
	 * @return 是否有效
	 */
	private static boolean isValidIdNumber(String idNumber) {
		// 身份证号长度必须为15位或18位
		if (idNumber.length() != 15 && idNumber.length() != 18) {
			return false;
		}
		
		// 如果是15位，必须全为数字
		if (idNumber.length() == 15) {
			return idNumber.matches("\\d{15}");
		}
		
		// 如果是18位，前17位必须为数字，最后一位可以是数字或X
		String regex = "\\d{17}[0-9X]";
		if (!idNumber.matches(regex)) {
			return false;
		}
		
		return true;
	}
}
