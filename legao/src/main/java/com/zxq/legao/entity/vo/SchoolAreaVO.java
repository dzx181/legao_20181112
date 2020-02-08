package com.zxq.legao.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolAreaVO implements Serializable {
    private Integer id;
    private String name;
    private String address;
    private String responPersonName;
    private String telphone;

}
