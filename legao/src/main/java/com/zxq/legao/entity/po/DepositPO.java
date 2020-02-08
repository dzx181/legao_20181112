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
 * 订金表
 * </p>
 *
 * @author dengzhenxiang
 * @since 2019-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("legao_deposit")
public class DepositPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 学生
     */
    @TableField("studentName")
    private String studentName;
    /**
     * 顾问
     */
    @TableField("adviser")
    private String adviser;
    /**
     * 金额
     */
    @TableField("moneyAmount")
    private String moneyAmount;
    /**
     * 支付方式
     */
    @TableField("payType")
    private String payType;
    /**
     * 备注
     */
    @TableField("remark")
    private String remark;


    public static final String ID = "id";

    public static final String STUDENTNAME = "studentName";

    public static final String ADVISER = "adviser";

    public static final String MONEYAMOUNT = "moneyAmount";

    public static final String PAYTYPE = "payType";

    public static final String REMARK = "remark";

}
