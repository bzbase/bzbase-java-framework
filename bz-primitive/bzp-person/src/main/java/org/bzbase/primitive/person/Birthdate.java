package org.bzbase.primitive.person;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.ValueObject;

import lombok.NonNull;
import lombok.Value;

/**
 * 出生日期
 *
 * @author legendjw
 */
@Value
public class Birthdate implements ValueObject {
    LocalDate date;

    /**
     * 根据日期创建一个出生日期
     *
     * @param date 日期
     * @return 返回一个新的Birthdate对象
     */
    public static Birthdate of(@NonNull LocalDate date) {
        if (date.isAfter(LocalDate.now())) {
            throw new DomainException("出生日期不能晚于当前日期！");
        }
        return new Birthdate(date);
    }

    /**
     * 计算并返回当前年龄
     *
     * @return 当前年龄，以整数形式表示
     */
    public int getAge() {
        return Period.between(date, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
