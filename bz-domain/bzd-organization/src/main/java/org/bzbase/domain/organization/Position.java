package org.bzbase.domain.organization;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bzbase.domain.organization.valueobject.PositionId;
import org.bzbase.library.ddd.type.AbstractAggregateRoot;
import org.bzbase.primitive.organization.OrganizationId;
import org.bzbase.primitive.tenant.TenantId;
import org.bzbase.primitive.user.UserId;

import java.time.Instant;

/**
 * 岗位聚合根
 */
@Getter
@Setter
@Builder(toBuilder = true)
public class Position extends AbstractAggregateRoot<PositionId> {
	/**
	 * 岗位ID
	 */
	private PositionId id;

	/**
	 * 租户ID
	 */
	private TenantId tenantId;

	/**
	 * 组织ID
	 */
	private OrganizationId organizationId;

	/**
	 * 岗位名称
	 */
	private String name;

	/**
	 * 上级岗位ID
	 */
    private PositionId parentId;

	/**
	 * 岗位编码
	 */
	private String code;

	/**
	 * 岗位描述
	 */
	private String description;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 创建人
	 */
	private UserId createdBy;

	/**
	 * 创建时间
	 */
	private Instant createdAt;
}