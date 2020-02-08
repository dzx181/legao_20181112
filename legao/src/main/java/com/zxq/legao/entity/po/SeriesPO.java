package com.zxq.legao.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SeriesPO {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String ageArea;
    private String teacherTools;
    /**
     * 课时
     */
    private String courseTime;
}
