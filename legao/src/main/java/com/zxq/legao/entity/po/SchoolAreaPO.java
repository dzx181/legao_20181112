package com.zxq.legao.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolAreaPO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String address;

    /**
     * 负责人
     */
    private String responPersonName;

    private String telphone;

}
