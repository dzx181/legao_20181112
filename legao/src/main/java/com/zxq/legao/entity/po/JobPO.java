package com.zxq.legao.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
/**
 * Description:
 * <p>
 *   职位
 * </p>
 * @author dengzhenxiang
 * @Date 2018/12/28 22:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobPO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Integer id;

    /**
     * 职位名称
     */
    private String name;

    /**
     * 评论
     */
    private String remark;

    /**
     * 创建日期
     */
    private Date createDate;
}
