package org.bzbase.domain.organization.service.impl;

import org.bzbase.domain.organization.Department;
import org.bzbase.domain.organization.infrastructure.DepartmentRepository;
import org.bzbase.domain.organization.service.DepartmentManageService;
import org.bzbase.domain.organization.valueobject.DepartmentId;
import org.bzbase.domain.organization.valueobject.EmployeeId;
import org.bzbase.domain.organization.valueobject.OrganizationId;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.IdGenerator;
import org.bzbase.primitive.user.UserId;

import lombok.RequiredArgsConstructor;

/**
 * 部门管理服务实现类
 */
@RequiredArgsConstructor
public class DepartmentManageServiceImpl implements DepartmentManageService {
    private final IdGenerator<String> idGenerator;
    private final DepartmentRepository departmentRepository;

    @Override
    public Department createDepartment(OrganizationId organizationId, String name, DepartmentId parentId,
            EmployeeId managerId, UserId currentUserId) {
        DepartmentId departmentId = new DepartmentId(idGenerator.generate());

        if (departmentRepository.existsByOrganizationIdAndName(organizationId, name)) {
            throw new DomainException("部门名称已经存在");
        }

        return Department.create(
                departmentId,
                organizationId,
                name,
                parentId,
                managerId,
                currentUserId);
    }

    @Override
    public Department modifyDepartment(DepartmentId departmentId, String name, DepartmentId parentId,
            EmployeeId managerId, UserId currentUserId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DomainException("指定的部门不存在"));

        boolean isNameChanged = !department.getName().equals(name);
        if (isNameChanged && departmentRepository.existsByOrganizationIdAndName(department.getOrganizationId(), name)) {
            throw new DomainException("部门名称已经存在");
        }

        department.modify(name, parentId, managerId, currentUserId);
        return department;
    }

    @Override
    public Department deleteDepartment(DepartmentId departmentId, UserId currentUserId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DomainException("指定的部门不存在"));

        if (departmentRepository.existsByOrganizationIdAndParentId(department.getOrganizationId(), departmentId)) {
            throw new DomainException("指定的部门存在子部门，不能删除");
        }

        department.delete(currentUserId);
        return department;
    }
}
