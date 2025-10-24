package org.bzbase.domain.rbac;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bzbase.domain.rbac.valueobject.GrantedPermission;
import org.bzbase.domain.rbac.valueobject.RoleId;
import org.bzbase.library.ddd.type.AbstractAggregateRoot;
import org.bzbase.primitive.organization.OrganizationId;
import org.bzbase.primitive.tenant.TenantId;
import org.bzbase.primitive.user.UserId;

import java.time.Instant;
import java.util.Set;

/**
 * 角色聚合根
 */
@Getter
@Setter
@Builder(toBuilder = true)
public class Role extends AbstractAggregateRoot<RoleId> {
	/**
	 * 角色ID
	 */
	private RoleId id;

	/**
	 * 租户ID
	 */
	private TenantId tenantId;

	/**
	 * 组织ID
	 */
	private OrganizationId organizationId;

	/**
	 * 父角色ID
	 */
	private RoleId parentId;

	/**
	 * 角色编码（唯一标识）
	 */
	private String code;

	/**
	 * 角色名称
	 */
	private String name;

	/**
	 * 角色描述
	 */
	private String description;

	/**
	 * 角色拥有的权限
	 */
	@Setter(AccessLevel.NONE)
	private Set<GrantedPermission> permissions;

	/**
	 * 创建人
	 */
	private UserId createdBy;

	/**
	 * 创建时间
	 */
	private Instant createdAt;

	/**
	 * 分配权限
	 * 
	 * @param permissions 授予的权限集合
	 */
	public void assignPermissions(Set<GrantedPermission> permissions) {
		this.permissions = permissions;
	}

	/**
	 * 判断是否有指定权限
	 * 
	 * @param permissionCode 权限编码
	 * @return 是否有权限
	 */
	public boolean hasPermission(String permissionCode) {
		return permissions != null && permissions.stream()
				.anyMatch(permission -> permission.getPermissionCode().equals(permissionCode));
	}
}
