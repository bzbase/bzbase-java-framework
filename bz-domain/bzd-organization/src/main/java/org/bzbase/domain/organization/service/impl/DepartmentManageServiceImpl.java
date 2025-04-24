package org.bzbase.domain.organization.service.impl;

import java.time.Instant;

import org.bzbase.domain.organization.Department;
import org.bzbase.domain.organization.infrastructure.DepartmentRepository;
import org.bzbase.domain.organization.service.DepartmentManageService;
import org.bzbase.domain.organization.valueobject.DepartmentId;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.IdGenerator;

import lombok.RequiredArgsConstructor;

/**
 * 部门管理服务实现类
 */
@RequiredArgsConstructor
public class DepartmentManageServiceImpl implements DepartmentManageService {
    private final IdGenerator<String> idGenerator;
    private final DepartmentRepository departmentRepository;

    @Override
    public Department createDepartment(Department department) {
        if (departmentRepository.existsByName(department.getTenantId(), department.getOrganizationId(),
                department.getName())) {
            throw new DomainException("部门名称已经存在");
        }

        if (department.getId() == null) {
            department.setId(new DepartmentId(idGenerator.generate()));
        }
        department.setCreatedAt(Instant.now());

        return department;
    }

    @Override
    public Department modifyDepartment(Department department) {
        Department oldDepartment = getDepartmentById(department.getId());

        boolean isNameChanged = !oldDepartment.getName().equals(department.getName());
        if (isNameChanged && departmentRepository.existsByName(department.getTenantId(), department.getOrganizationId(),
                department.getName())) {
            throw new DomainException("部门名称已经存在");
        }

        return department;
    }

    @Override
    public Department deleteDepartment(DepartmentId departmentId) {
        Department department = getDepartmentById(departmentId);

        if (departmentRepository.existsByParentId(department.getTenantId(), department.getOrganizationId(),
                departmentId)) {
            throw new DomainException("指定的部门存在子部门，不能删除");
        }

        return department;
    }

    private Department getDepartmentById(DepartmentId departmentId) {
        return departmentRepository.findById(departmentId).orElseThrow(() -> new DomainException("指定的部门不存在"));
    }
}
