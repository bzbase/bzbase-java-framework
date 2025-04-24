package org.bzbase.domain.rbac.valueobject;

import lombok.Value;

/**
 * 数据范围值对象
 */
@Value
public class DataScope {
    /**
     * 数据范围编码
     */
    private String code;

    /**
     * 数据范围名称
     */
    private String name;

    /**
     * 数据范围优先级
     * 数值越小优先级越高,默认为0
     */
    private Integer priority;
} 