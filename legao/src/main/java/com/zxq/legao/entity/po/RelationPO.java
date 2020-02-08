package com.zxq.legao.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Description:
 * <p>
 *   对应关系
 * </p>
 * @author dengzhenxiang
 * @Date 2018/12/28 22:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelationPO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Integer id;

    /**
     * 校区id
     */
    private Integer scheduleID;

    /**
     * 学生id
     */
    private Integer studentID;

    /**
     * 签到状态：1准时，2未签到，3请假
     */
    private Integer signInStatus;

    /**
     * 接受页面选中的caption
     */
    private List<Integer> caption;

    /**
     * 老师id,计算课时
     */
    private Integer teacherID;

    /**
     * 课程课时，计算课时
     */
    private String classTimes;

    /**
     * 周数
     */
    private String weekOfYear;

    /**
     * 传送前台来的时间段
     */
    private String timeSection;
    /**
     * 创建日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    /**
     * 最后一次修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastModifyTime;
    /**
     * 修改人
     */
    private String modifyPerson;
    private String lastModifyTimeStr;
}
