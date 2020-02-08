package com.zxq.legao.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
/**
 * Description:
 * <p>
 *   课程
 * </p>
 * @author dengzhenxiang
 * @Date 2018/12/28 22:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CoursePO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 课程名
     */
    private String name;

    /**
     * 年龄段
     */
    private String ageArea;

    /**
     * 教具
     */
    private String teacherTools;

    /**
     * 课时
     */
    private String courseTime;

    /**
     * 课程系列id
     */
    private Integer seriesID;


}
