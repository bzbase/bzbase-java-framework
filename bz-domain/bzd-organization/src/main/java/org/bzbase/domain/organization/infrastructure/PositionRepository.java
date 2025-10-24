package org.bzbase.domain.organization.infrastructure;

import org.bzbase.domain.organization.Position;
import org.bzbase.domain.organization.valueobject.PositionId;
import org.bzbase.library.ddd.type.Repository;

import java.util.List;

/**
 * 岗位资源库
 */
public interface PositionRepository extends Repository<Position, PositionId> {
    /**
     * 判断是否存在指定名称的岗位
     *
     * @param name           岗位名称
     * @return 是否存在
     */
    boolean existsByName(String name);
    
    /**
     * 根据父岗位ID查找子岗位
     *
     * @param parentId       父岗位ID
     * @return 子岗位列表
     */
    List<Position> findByParentId(PositionId parentId);
    
    /**
     * 判断指定组织下是否存在指定父岗位ID的岗位
     *
     * @param parentId       父岗位ID
     * @return 是否存在
     */
    boolean existsByParentId(PositionId parentId);
}
