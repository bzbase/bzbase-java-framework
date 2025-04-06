package org.bzbase.domain.organization.valueobject;

import org.bzbase.primitive.person.IdCard;

import lombok.Builder;
import lombok.Value;
import lombok.With;

/**
 * 员工证件信息值对象
 */
@Value
@With
@Builder
public class EmployeeCredentialInfo {
    /**
     * 身份证
     */
    private IdCard idCard;

    /**
     * 银行卡
     */
    private BankCard bankCard;
}