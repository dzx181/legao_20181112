package com.zxq.legao.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DateVO {
    private Integer id;
    private Integer status;
    private String timeSection;
    private String remark;
    /**
     * 第几周
     */
    private String weekOfYear;

    /**
     * 本周的起始时间
     */
    private String startWeekDate;

    /**
     * 本周的结束时间
     */
    private String endWeekDate;

    /**
     * 优先级，取值为数字
     */
    private Integer priority;

}
