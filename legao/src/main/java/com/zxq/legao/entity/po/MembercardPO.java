package com.zxq.legao.entity.po;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 会员卡表
 * </p>
 *
 * @author dengzhenxiang
 * @since 2019-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("legao_membercard")
public class MembercardPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 会员卡编号
     */
    @TableField("memberCardCode")
    private String memberCardCode;
    /**
     * 会员卡类型
     */
    @TableField("carType")
    private String carType;
    /**
     * 学生
     */
    @TableField("studentName")
    private String studentName;
    /**
     * 课时
     */
    @TableField("classTime")
    private String classTime;


    public static final String ID = "id";

    public static final String MEMBERCARDCODE = "memberCardCode";

    public static final String CARTYPE = "carType";

    public static final String STUDENTNAME = "studentName";

    public static final String CLASSTIME = "classTime";

}
