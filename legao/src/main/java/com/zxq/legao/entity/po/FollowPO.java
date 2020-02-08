package com.zxq.legao.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
/**
 * Description:
 * <p>
 *   跟进
 * </p>
 * @author dengzhenxiang
 * @Date 2018/12/28 22:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowPO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 学生名
     */
    private String studentName;

    /**
     * 顾问
     */
    private String advisor;

    /**
     * 日期
     */
    private Date date;

    /**
     * 跟进方式
     */
    private String mode;

    /**
     * 更进内容
     */
    private String content;


}
