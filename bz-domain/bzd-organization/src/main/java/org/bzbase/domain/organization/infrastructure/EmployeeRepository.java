package org.bzbase.domain.organization.infrastructure;

import org.bzbase.domain.organization.Employee;
import org.bzbase.domain.organization.valueobject.EmployeeId;
import org.bzbase.library.ddd.type.Repository;
import org.bzbase.primitive.emailaddress.EmailAddress;
import org.bzbase.primitive.organization.OrganizationId;
import org.bzbase.primitive.person.IdNumber;
import org.bzbase.primitive.phonenumber.PhoneNumber;
import org.bzbase.primitive.user.UserId;

import java.util.Optional;

/**
 * 员工资源库
 *
 * @author legendjw
 */
public interface EmployeeRepository extends Repository<Employee, EmployeeId> {
	/**
     * 根据用户账号ID查询员工
     *
     * @param organizationId 组织ID
     * @param userAccountId  用户账号ID
     * @return 员工
     */
	Optional<Employee> findByUserAccountId(OrganizationId organizationId, UserId userAccountId);

	/**
	 * 判断是否存在指定手机号码的员工
	 *
	 * @param phoneNumber    手机号码
	 * @return 如果存在返回true，否则返回false
	 */
	boolean existsByPhoneNumber(PhoneNumber phoneNumber);

	/**
	 * 判断是否存在指定邮箱的员工
	 *
	 * @param emailAddress   邮箱地址
	 * @return 如果存在返回true，否则返回false
	 */
	boolean existsByEmailAddress(EmailAddress emailAddress);

	/**
	 * 判断是否存在指定工号的员工
	 *
	 * @param employeeNumber 工号
	 * @return 如果存在返回true，否则返回false
	 */
	boolean existsByEmployeeNumber(String employeeNumber);

	/**
	 * 判断是否存在指定身份证号码的员工
	 *
	 * @param idNumber       身份证号码
	 * @return 如果存在返回true，否则返回false
	 */
	boolean existsByIdNumber(IdNumber idNumber);
}
