package com.zxq.legao.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Description:
 * <p>
 *   课程时间段
 * </p>
 * @author dengzhenxiang
 * @Date 2018/12/28 22:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DatePO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     *状态
     */
    private Integer status;

    /**
     *时间段，接受课程时间段的起始时间
     * 10:04
     */
    private String timeSection;

    /**
     *时间段，接受课程时间段的结束时间
     * 04:04
     * 与timeSection拼接结果为10:04 - 04:04
     */
    private String timeSection1;

    /**
     *评论
     */
    private String remark;

    /**
     * 优先级，取值为数字
     */
    private Integer priority;
}
