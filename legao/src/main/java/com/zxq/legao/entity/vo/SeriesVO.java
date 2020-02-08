package com.zxq.legao.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SeriesVO {
    private Integer id;
    private String name;
    private String ageArea;
    private String teacherTools;
    private String courseTime;
}
