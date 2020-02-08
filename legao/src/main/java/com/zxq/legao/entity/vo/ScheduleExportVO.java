package com.zxq.legao.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ScheduleExportVO {
    /**
     * 学生
     */
    private String studentName;
    /**
     * 年龄段
     */
    private String ageArea;
    /**
     * 上课时间
     */
    private String courseTime;
    /**
     * 教室
     */
    private String classRoom;
    /**
     * 签到状态
     */
    private String signStatus;
    /**
     * 星期
     */
    private String week;
    /**
     * 校区
     */
    private String schoolArea;
    /**
     * 老师
     */
    private String teacherName;
    /**
     * 性别
     */
    private String sex;
    /**
     * 课程
     */
    private String courseName;
}
