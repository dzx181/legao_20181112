package com.zxq.legao.entity.vo;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("legao_employ")
public class ClassRoomVO implements Serializable {
    private Integer id;
    private String name;
    private String ageArea;
    private String responsePersonName;

}