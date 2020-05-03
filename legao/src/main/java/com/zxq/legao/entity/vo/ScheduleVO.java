package com.zxq.legao.entity.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleVO {
    private Integer id;
    private SchoolAreaVO schoolArea;
    private DateVO date;
    private CourseVO course;
    private ClassRoomVO classroom;
    private List<ScheduleVO> student;
    private Date courseDate;
    private EmployVO teacherVO;
    private String courseWeek;
    private String weekOfYear;
    private SeriesVO series;
    /**
     * 学生总人数
     */
    private Integer totalStu;
    /**
     * 本周开始时间
     */
    private String weekStartTime;
    /**
     * 本周结束时间
     */
    private String weekEndTime;
    /**
     * 所有学生名
     */
    private String studengNames;

    private String thisYear;
    private Integer schoolAreaID;
    private Integer dateID;
    private Integer courseID;
    private Integer classroomID;
    private Integer seriesID;
    private Integer teacherID;


    public void setWeekOfYear(String weekOfYear) {
        if (weekOfYear != null && !"".equals(weekOfYear)) {
            this.weekOfYear = weekOfYear.substring(4);
            this.thisYear = weekOfYear.substring(0, 4);
        }
    }
}
