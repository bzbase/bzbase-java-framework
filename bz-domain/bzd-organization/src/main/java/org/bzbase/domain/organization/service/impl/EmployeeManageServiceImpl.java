package org.bzbase.domain.organization.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.bzbase.domain.organization.Employee;
import org.bzbase.domain.organization.infrastructure.EmployeeRepository;
import org.bzbase.domain.organization.service.EmployeeManageService;
import org.bzbase.domain.organization.valueobject.EmployeeId;
import org.bzbase.domain.organization.valueobject.EmployeeStatus;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.IdGenerator;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * 员工管理服务实现类
 */
@RequiredArgsConstructor
public class EmployeeManageServiceImpl implements EmployeeManageService {
    private final IdGenerator<String> idGenerator;
    private final EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        if (employee.getBasicInfo().getPhoneNumber() != null
                && employeeRepository.existsByPhoneNumber(employee.getBasicInfo().getPhoneNumber())) {
            throw new DomainException("手机号码已经存在");
        }

        if (employee.getBasicInfo().getEmailAddress() != null
                && employeeRepository.existsByEmailAddress(employee.getBasicInfo().getEmailAddress())) {
            throw new DomainException("邮箱地址已经存在");
        }

        if (StringUtils.isNotBlank(employee.getJobInfo().getNumber())
                && employeeRepository.existsByEmployeeNumber(employee.getJobInfo().getNumber())) {
            throw new DomainException("工号已经存在");
        }

        if (employee.getCredentialInfo().getIdCard() != null
                && employee.getCredentialInfo().getIdCard().getIdNumber() != null
                && employeeRepository.existsByIdNumber(employee.getCredentialInfo().getIdCard().getIdNumber())) {
            throw new DomainException("身份证号码已经存在");
        }

        if (employee.getId() == null) {
            employee.setId(new EmployeeId(idGenerator.generate()));
        }
        employee.setStatus(EmployeeStatus.ACTIVE);
        employee.setCreatedAt(Instant.now());

        return employee;
    }

    @Override
    public Employee modifyEmployee(Employee employee) {
        Employee oldEmployee = getEmployeeById(employee.getId());

        // 检查手机号是否重复
		boolean isPhoneNumberChanged = !oldEmployee.getBasicInfo().getPhoneNumber().equals(employee.getBasicInfo().getPhoneNumber());
		if (isPhoneNumberChanged && employeeRepository.existsByPhoneNumber(employee.getBasicInfo().getPhoneNumber())) {
			throw new DomainException("手机号码已经存在");
		}

		// 检查邮箱是否重复
		if (employee.getBasicInfo().getEmailAddress() != null) {
			boolean isEmailAddressChanged = !Objects.equals(oldEmployee.getBasicInfo().getEmailAddress(),
					employee.getBasicInfo().getEmailAddress());
			if (isEmailAddressChanged && employeeRepository.existsByEmailAddress(employee.getBasicInfo().getEmailAddress())) {
				throw new DomainException("邮箱地址已经存在");
			}
		}

		// 检查工号是否重复
		if (StringUtils.isNotBlank(employee.getJobInfo().getNumber())) {
			boolean isNumberChanged = !Objects.equals(oldEmployee.getJobInfo().getNumber(), employee.getJobInfo().getNumber());
			if (isNumberChanged && employeeRepository.existsByEmployeeNumber(employee.getJobInfo().getNumber())) {
				throw new DomainException("工号已经存在");
			}
		}

		// 检查身份证号是否重复
		if (employee.getCredentialInfo().getIdCard() != null && employee.getCredentialInfo().getIdCard().getIdNumber() != null) {
			boolean isIdCardNumberChanged = oldEmployee.getCredentialInfo().getIdCard() == null
					|| !Objects.equals(oldEmployee.getCredentialInfo().getIdCard().getIdNumber(),
							employee.getCredentialInfo().getIdCard().getIdNumber());
			if (isIdCardNumberChanged && employeeRepository.existsByIdNumber(employee.getCredentialInfo().getIdCard().getIdNumber())) {
				throw new DomainException("身份证号已经存在");
			}
		}

        return employee;
    }

    @Override
    public Employee deleteEmployee(EmployeeId employeeId) {
        Employee employee = getEmployeeById(employeeId);

        return employee;
    }

    @Override
    public Employee onboardEmployee(EmployeeId employeeId, LocalDate onboardDate) {
        Employee employee = getEmployeeById(employeeId);

        employee.onboard(onboardDate);

        return employee;
    }

    @Override
    public Employee offboardEmployee(EmployeeId employeeId, LocalDate offboardDate, String reason) {
        Employee employee = getEmployeeById(employeeId);

        employee.offboard(offboardDate, reason);

        return employee;
    }

    public Employee getEmployeeById(EmployeeId employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new DomainException("指定的员工不存在"));
    }
}
