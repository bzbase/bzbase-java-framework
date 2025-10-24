package org.bzbase.primitive.enterprise;

import lombok.Value;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.ValueObject;

import java.time.LocalDate;

/**
 * 营业期限值对象
 */
@Value
public class BusinessTerm implements ValueObject {
    /**
     * 营业期限开始日期
     */
    LocalDate startDate;
    /**
     * 营业期限结束日期，长期营业时为 null
     */
    LocalDate endDate;

    /**
     * 使用已校验的起止日期创建营业期限
     *
     * @param startDate 开始日期
     * @param endDate 结束日期
     */
    private BusinessTerm(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * 创建营业期限值对象，自动校验日期区间
     *
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 营业期限值对象
     */
    public static BusinessTerm of(LocalDate startDate, LocalDate endDate) {
        if (startDate == null) {
            throw new DomainException("营业期限开始日期不能为空");
        }

        if (endDate != null && endDate.isBefore(startDate)) {
            throw new DomainException("营业期限结束日期不能早于开始日期");
        }

        return new BusinessTerm(startDate, endDate);
    }

    /**
     * 判断营业期限是否长期有效
     *
     * @return true 表示长期有效
     */
    public boolean isLongTerm() {
        return endDate == null;
    }
}
