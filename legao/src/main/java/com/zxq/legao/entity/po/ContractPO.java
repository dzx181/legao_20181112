package com.zxq.legao.entity.po;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 合同表
 * </p>
 *
 * @author dengzhenxiang
 * @since 2019-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("legao_contract")
public class ContractPO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 合同编号
     */
    @TableField("contractCode")
    private String contractCode;
    /**
     * 学生
     */
    @TableField("studentId")
    private String studentId;
    /**
     * 签约日期
     */
    @TableField("signDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date signDate;
    /**
     * 课时包，关联课时包表id
     */
    @TableField("classPackageId")
    private Integer classPackageId;
    /**
     * 价格
     */
    @TableField("price")
    private String price;
    /**
     * 折扣
     */
    @TableField("discount")
    private String discount;
    /**
     * 订金id,关联订金表id
     */
    @TableField("depositId")
    private Integer depositId;
    /**
     * 实付金额
     */
    @TableField("act_pay")
    private String actPay;
    /**
     * 支付方式
     */
    @TableField("payType")
    private String payType;
    /**
     * 赠送课时
     */
    @TableField("presentationClassTime")
    private String presentationClassTime;
    /**
     * 起始日期
     */
    @TableField("startTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    /**
     * 终止日期
     */
    @TableField("endTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    /**
     * 业绩所属
     */
    @TableField("belongOne")
    private String belongOne;
    /**
     * 会员卡id，关联会员卡表id
     */
    @TableField("memberCarId")
    private Integer memberCarId;
    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
    /**
     * 课时
     */
    private String classTime;
    /**
     * 剩余课时
     */
    private String remainClassTime;
    /**
     * 校区id关联到校区表
     */
    private Integer schoolAreaId;
    /**
     * 总课时
     */
    private String totalClassTime;

    private String signDateStr;
    private String startTimeStr;
    private String endTimeStr;


    public static final String ID = "id";

    public static final String CONTRACTCODE = "contractCode";

    public static final String STUDENTNAME = "studentName";

    public static final String SIGNDATE = "signDate";

    public static final String CLASSPACKAGEID = "classPackageId";

    public static final String PRICE = "price";

    public static final String DISCOUNT = "discount";

    public static final String DEPOSITID = "depositId";

    public static final String ACT_PAY = "act_pay";

    public static final String PAYTYPE = "payType";

    public static final String PRESENTATIONCLASSTIME = "presentationClassTime";

    public static final String STARTTIME = "startTime";

    public static final String ENDTIME = "endTime";

    public static final String BELONGONE = "belongOne";

    public static final String MEMBERCARID = "memberCarId";

    public static final String REMARK = "remark";

}
