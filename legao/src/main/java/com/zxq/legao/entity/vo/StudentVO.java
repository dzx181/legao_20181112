package com.zxq.legao.entity.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentVO implements Serializable {
    private Integer id;
    private String nickName;
    private String name;
    private String parentName;
    private String parentRelat;
    private String sex;
    private String telphone;
    private String weChatID;
    private String education;
    private Date birthday;
    private Date createDate;
    private String sparePhone;
    private String markPeople;
    private String advisor;
    private String area;
    private Date willDate;
    private String baseSituation;
    private Integer status;
    private String source;
    private Integer importanceGrade;
    private String teacherName;
    private CourseVO course;
    private SchoolAreaVO schoolAreaID;
    private EmployVO followID;
    private String age;
    private Integer follow;
    private String stuAge;


}
