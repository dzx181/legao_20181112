package com.zxq.legao.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dengzhenxiang
 * @Date 2018/12/5 14:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptVO implements Serializable {
    private Integer id;
    private String name;
    /**
     * 部门职能
     */

    private String remark;
    private Date createDate;
    /**
     * 部门总人数
     */
    private Integer deptAllNum;
    private Integer deptID;


}
