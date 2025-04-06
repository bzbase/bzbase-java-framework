package org.bzbase.domain.organization;

import org.bzbase.domain.organization.valueobject.DepartmentId;
import org.bzbase.domain.organization.valueobject.EmployeeId;
import org.bzbase.domain.organization.valueobject.OrganizationId;
import org.bzbase.library.ddd.type.LifecycleAggregateRoot;
import org.bzbase.primitive.user.UserId;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * 部门聚合根
 */
@Getter
@SuperBuilder(toBuilder = true)
public class Department extends LifecycleAggregateRoot<DepartmentId> {
    /**
     * 部门ID
     */
    private DepartmentId id;

    /**
     * 所属组织ID
     */
    private OrganizationId organizationId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 上级部门ID
     */
    private DepartmentId parentId;

    /**
     * 部门主管ID
     */
    private EmployeeId managerId;

    /**
     * 创建部门
     *
     * @param id             部门ID
     * @param organizationId 组织ID
     * @param name           部门名称
     * @param parentId       上级部门ID
     * @param managerId      部门主管ID
     * @param currentUserId  当前用户ID
     * @return 部门
     */
    public static Department create(DepartmentId id, OrganizationId organizationId, String name, DepartmentId parentId,
            EmployeeId managerId, UserId currentUserId) {
        Department department = Department.builder()
                .id(id)
                .organizationId(organizationId)
                .name(name)
                .parentId(parentId)
                .managerId(managerId)
                .build();
        department.markAsCreated(currentUserId);
        return department;
    }

    /**
     * 修改部门信息
     *
     * @param name          部门名称
     * @param parentId      上级部门ID
     * @param managerId     部门主管ID
     * @param currentUserId 当前用户ID
     */
    public void modify(String name, DepartmentId parentId, EmployeeId managerId, UserId currentUserId) {
        this.name = name;
        this.parentId = parentId;
        this.managerId = managerId;
        this.markAsUpdated(currentUserId);
    }

    /**
     * 删除部门
     *
     * @param currentUserId 当前用户ID
     */
    public void delete(UserId currentUserId) {
        this.markAsDeleted(currentUserId);
    }
}
