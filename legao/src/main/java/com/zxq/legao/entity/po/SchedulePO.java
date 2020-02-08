package com.zxq.legao.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchedulePO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer schoolAreaID;
    private Integer dateID;
    private Integer courseID;
    private Integer classroomID;
    private Integer seriesID;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date courseDate;
    /**
     * 课程所在的星期
     */
    private String courseWeek;
    /**
     * 老师
     */
    private Integer teacherID;
    /**
     * 在一年中所在的周数
     */
    private String weekOfYear;
    /**
     * 学员
     */
    private List<Integer> studentList;
    /**
     * 更改人
     */
    private String modifyPerson;

}
