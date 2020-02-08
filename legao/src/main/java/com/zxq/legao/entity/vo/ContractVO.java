package com.zxq.legao.entity.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.zxq.legao.entity.po.ClasstimepackPO;
import com.zxq.legao.entity.po.DepositPO;
import com.zxq.legao.entity.po.MembercardPO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class ContractVO implements Serializable {

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
    private StudentVO studentVO;
    /**
     * 签约日期
     */
    @TableField("signDate")
    private Date signDate;
    /**
     * 课时包，关联课时包表id
     */
    @TableField("classPackageId")
    private ClasstimepackPO classtimepackVO;
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
    private DepositPO depositVO;
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
    private Date startTime;
    /**
     * 终止日期
     */
    @TableField("endTime")
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
    private MembercardPO membercardVO;
    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
    /**
     * 订金表id
     */
    private Integer depositId;
    /**
     * 会员卡id
     */
    private Integer memberCarId;
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
    private SchoolAreaVO schoolArea;
    /**
     * 总课时
     */
    private String totalClassTime;
    /**
     * 学员id
     */
    private Integer studentId;
    /**
     * 课时包id
     */
    private Integer classPackageId;
    /**
     * 校区id
     */
    private Integer schoolAreaId;


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
