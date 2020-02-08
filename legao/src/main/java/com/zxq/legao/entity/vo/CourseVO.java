package com.zxq.legao.entity.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CourseVO {
    private Integer id;
    private String name;
    private String ageArea;
    private String teacherTools;
    private String courseTime;
    private SeriesVO series;
    /**
     * 学生总人数
     */
    private Integer studentNum;


}
