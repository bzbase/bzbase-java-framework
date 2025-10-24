package org.bzbase.domain.organization.infrastructure;

import org.bzbase.domain.organization.Department;
import org.bzbase.domain.organization.valueobject.DepartmentId;
import org.bzbase.library.ddd.type.Repository;

import java.util.List;

/**
 * 部门资源库
 *
 * @author legendjw
 */
public interface DepartmentRepository extends Repository<Department, DepartmentId> {
    /**
     * 查找所有部门
     *
     * @return 部门列表
     */
    List<Department> find();

    /**
     * 根据父部门ID查找子部门
     *
     * @param parentId       父部门ID
     * @return 子部门列表
     */
    List<Department> findByParentId(DepartmentId parentId);

    /**
     * 判断是否存在指定名称的部门
     *
     * @param name           部门名称
     * @return 是否存在
     */
    boolean existsByName(String name);

    /**
     * 判断是否存在指定父部门ID的部门
     *
     * @param parentId       父部门ID
     * @return 是否存在
     */
    boolean existsByParentId(DepartmentId parentId);
}