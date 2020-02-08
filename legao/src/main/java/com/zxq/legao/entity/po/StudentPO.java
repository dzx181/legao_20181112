package com.zxq.legao.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentPO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nickName;
    private String name;
    private String parentName;
    /**
     * 家长关系
     */
    private String parentRelat;
    private String sex;
    private String telphone;
    /**
     * 微信
     */
    private String weChatID;
    private String education;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    /**
     * 备用电话
     */
    private String sparePhone;
    /**
     * 市场人员
     */
    private String markPeople;
    /**
     * 顾问
     */
    private String advisor;
    private String area;
    /**
     * 期望日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date willDate;
    private String baseSituation;
    private Integer status;
    private String source;
    /**
     * 重要程度
     */
    private Integer importanceGrade;
    private String teacherName;
    private Integer courseID;
    private Integer schoolAreaID;
    private Integer followID;
    /**
     * 排序字段
     */
    private String ifImportanceGradeOrder;
    private String stuAge;


}
