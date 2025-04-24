package org.bzbase.domain.rbac.valueobject;

import lombok.Value;

/**
 * 授予的权限值对象
 */
@Value
public class GrantedPermission {
    /**
     * 权限编码
     */
    private String permissionCode;

    /**
     * 数据范围编码
     */
    private String dataScopeCode;

    /**
     * 自定义数据
     */
    private Object customData;
} 