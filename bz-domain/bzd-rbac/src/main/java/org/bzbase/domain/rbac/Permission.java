package org.bzbase.domain.rbac;

import java.time.Instant;
import java.util.List;

import org.bzbase.domain.rbac.valueobject.DataScope;
import org.bzbase.domain.rbac.valueobject.PermissionId;
import org.bzbase.library.ddd.type.AbstractAggregateRoot;
import org.bzbase.primitive.user.UserId;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 权限聚合根
 */
@Getter
@Builder(toBuilder = true)
@Setter
public class Permission extends AbstractAggregateRoot<PermissionId> {
	/**
	 * 权限ID
	 */
	private PermissionId id;

	/**
	 * 父权限ID
	 */
	private PermissionId parentId;

	/**
	 * 权限编码（唯一标识）
	 */
	private String code;

	/**
	 * 权限名称
	 */
	private String name;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 数据范围列表
	 */
	private List<DataScope> dataScopes;

	/**
	 * 创建人
	 */
	private UserId createdBy;

	/**
	 * 创建时间
	 */
	private Instant createdAt;

	/**
	 * 判断是否设置了权限范围
	 * 
	 * @return 是否设置了权限范围
	 */
	public boolean hasDataScope() {
		return dataScopes != null && !dataScopes.isEmpty();
	}
}