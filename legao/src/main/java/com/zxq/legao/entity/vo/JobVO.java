package com.zxq.legao.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobVO implements Serializable {
    private Integer id;
    private String name;
    private String remark;
    private Date createDate;
    /**
     *  获得该职称总人数
     */
    private Integer jobAllNum;

}