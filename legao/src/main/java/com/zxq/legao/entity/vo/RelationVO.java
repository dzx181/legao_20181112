package com.zxq.legao.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelationVO {
    private Integer id;
    private ScheduleVO schedule;
    private StudentVO student;
    private Integer signInStatus;
    /**
     * 创建日期
     */
    private Date createDate;
    /**
     * 最后一次修改时间
     */
    private Date lastModifyTime;
    /**
     * 修改人
     */
    private String modifyPerson;

}
