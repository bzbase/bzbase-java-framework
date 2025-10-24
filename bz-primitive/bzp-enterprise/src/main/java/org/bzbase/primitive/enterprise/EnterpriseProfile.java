package org.bzbase.primitive.enterprise;

import lombok.Value;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.ValueObject;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 企业档案值对象，记录企业的基本信息
 */
@Value
public class EnterpriseProfile implements ValueObject {
    /**
     * 企业统一社会信用代码
     */
    UnifiedSocialCreditCode unifiedSocialCreditCode;
    /**
     * 企业名称
     */
    EnterpriseName enterpriseName;
    /**
     * 企业类型，可为空
     */
    EnterpriseType enterpriseType;
    /**
     * 企业法人代表信息
     */
    LegalRepresentative legalRepresentative;
    /**
     * 注册资本，可为空
     */
    BigDecimal registeredCapital;
    /**
     * 成立日期，可为空
     */
    LocalDate establishmentDate;
    /**
     * 注册地址，可为空
     */
    String registeredAddress;
    /**
     * 营业期限，可为空
     */
    BusinessTerm businessTerm;

    /**
     * 使用给定信息创建企业档案
     *
     * @param unifiedSocialCreditCode 统一社会信用代码
     * @param enterpriseName 企业名称
     * @param enterpriseType 企业类型，可为空
     * @param legalRepresentative 法人代表
     * @param registeredCapital 注册资本，可为空
     * @param establishmentDate 成立日期，可为空
     * @param registeredAddress 注册地址，可为空
     * @param businessTerm 营业期限，可为空
     * @return 企业档案值对象
     */
    public static EnterpriseProfile of(UnifiedSocialCreditCode unifiedSocialCreditCode,
                                       EnterpriseName enterpriseName,
                                       EnterpriseType enterpriseType,
                                       LegalRepresentative legalRepresentative,
                                       BigDecimal registeredCapital,
                                       LocalDate establishmentDate,
                                       String registeredAddress,
                                       BusinessTerm businessTerm) {
        return new EnterpriseProfile(unifiedSocialCreditCode,
                enterpriseName,
                enterpriseType,
                legalRepresentative,
                registeredCapital,
                establishmentDate,
                registeredAddress,
                businessTerm);
    }

    /**
     * 私有构造函数
     *
     * @param unifiedSocialCreditCode 统一社会信用代码
     * @param enterpriseName 企业名称
     * @param enterpriseType 企业类型，可为空
     * @param legalRepresentative 法人代表
     * @param registeredCapital 注册资本，可为空
     * @param establishmentDate 成立日期，可为空
     * @param registeredAddress 注册地址，可为空
     * @param businessTerm 营业期限，可为空
     */
    private EnterpriseProfile(UnifiedSocialCreditCode unifiedSocialCreditCode,
                              EnterpriseName enterpriseName,
                              EnterpriseType enterpriseType,
                              LegalRepresentative legalRepresentative,
                              BigDecimal registeredCapital,
                              LocalDate establishmentDate,
                              String registeredAddress,
                              BusinessTerm businessTerm) {
        this.unifiedSocialCreditCode = unifiedSocialCreditCode;
        this.enterpriseName = enterpriseName;
        this.enterpriseType = enterpriseType;
        this.legalRepresentative = legalRepresentative;
        this.registeredCapital = registeredCapital;
        this.establishmentDate = establishmentDate;
        this.registeredAddress = registeredAddress;
        this.businessTerm = businessTerm;
    }
}
