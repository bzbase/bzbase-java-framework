package org.bzbase.domain.organization.valueobject;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Value;
import lombok.With;

/**
 * 员工工作信息值对象
 */
@Value
@With
@Builder
public class EmployeeJobInfo {
    /**
     * 部门ID
     */
    private DepartmentId departmentId;

    /**
     * 岗位ID
     */
    private PositionId positionId;

    /**
     * 工号
     */
    private String number;

    /**
     * 入职日期
     */
    private LocalDate onboardDate;

    /**
     * 离职日期
     */
    private LocalDate offboardDate;

    /**
     * 离职原因
     */
    private String offboardReason;
}
