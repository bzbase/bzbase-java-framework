package org.bzbase.domain.organization.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.bzbase.domain.organization.Employee;
import org.bzbase.domain.organization.infrastructure.EmployeeRepository;
import org.bzbase.domain.organization.service.EmployeeManageService;
import org.bzbase.domain.organization.valueobject.EmergencyContact;
import org.bzbase.domain.organization.valueobject.EmployeeBasicInfo;
import org.bzbase.domain.organization.valueobject.EmployeeCredentialInfo;
import org.bzbase.domain.organization.valueobject.EmployeeId;
import org.bzbase.domain.organization.valueobject.EmployeeJobInfo;
import org.bzbase.domain.organization.valueobject.OrganizationId;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.IdGenerator;
import org.bzbase.primitive.user.UserId;

import lombok.RequiredArgsConstructor;

/**
 * 员工管理服务实现类
 */
@RequiredArgsConstructor
public class EmployeeManageServiceImpl implements EmployeeManageService {
    private final IdGenerator<String> idGenerator;
    private final EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(OrganizationId organizationId, EmployeeBasicInfo basicInfo,
            EmployeeJobInfo jobInfo,
            EmployeeCredentialInfo credentialInfo, List<EmergencyContact> emergencyContacts,
            UserId currentUserId) {
        EmployeeId employeeId = new EmployeeId(idGenerator.generate());

        return Employee.create(
                employeeId,
                organizationId,
                basicInfo,
                jobInfo,
                credentialInfo,
                emergencyContacts,
                currentUserId);
    }

    @Override
    public Employee modifyEmployee(EmployeeId employeeId, EmployeeBasicInfo basicInfo, EmployeeJobInfo jobInfo,
            EmployeeCredentialInfo credentialInfo, List<EmergencyContact> emergencyContacts,
            UserId currentUserId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new DomainException("指定的员工不存在"));

        employee.modify(basicInfo, jobInfo, credentialInfo, emergencyContacts, currentUserId);

        return employee;
    }

    @Override
    public Employee deleteEmployee(EmployeeId employeeId, UserId currentUserId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new DomainException("指定的员工不存在"));

        employee.delete(currentUserId);

        return employee;
    }

    @Override
    public Employee onboardEmployee(EmployeeId employeeId, LocalDate onboardDate, UserId currentUserId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new DomainException("指定的员工不存在"));

        employee.onboard(onboardDate, currentUserId);

        return employee;
    }

    @Override
    public Employee offboardEmployee(EmployeeId employeeId, LocalDate offboardDate, String reason,
            UserId currentUserId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new DomainException("指定的员工不存在"));

        employee.offboard(offboardDate, reason, currentUserId);

        return employee;
    }
}
