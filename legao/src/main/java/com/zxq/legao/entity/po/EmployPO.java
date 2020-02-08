package com.zxq.legao.entity.po;


import com.baomidou.mybatisplus.activerecord.Model;
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
 *     员工
 * </p>
 *
 * @author dengzhenxiang
 * @since 2018-11-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("legao_employ")
public class EmployPO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     *员工姓名
     */
    private String name;

    /**
     *员工性别
     */
    private String sex;

    /**
     *电话
     */
    private String telphone;

    /**
     * 生日
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 入职日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date entryDate;

    /**
     * 关联legao_job表
     */

    private Integer jobID;

    /**
     * 基本工资
     */

    private String basicSalary;

    /**
     * 是否全勤
     */

    private String fullWork;

    /**
     * 餐补
     */

    private String eatAllow;

    /**
     * 课时费
     */

    private String classPay;

    /**
     * 总课时
     */

    private String allClassTime;

    /**
     * 提成
     */

    private String extraPay;

    /**
     * 校区
     */
    private String area;

    /**
     * 状态：1表在职，2表离职，3表其他
     */
    private Integer status;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String SEX = "sex";

    public static final String TELPHONE = "telphone";

    public static final String BIRTHDAY = "birthday";

    public static final String ENTRYDATE = "entryDate";

    public static final String JOBID = "jobID";

    public static final String BASICSALARY = "basicSalary";

    public static final String FULLWORK = "fullWork";

    public static final String EATALLOW = "EatAllow";

    public static final String CLASSPAY = "classPay";

    public static final String ALLCLASSTIME = "allClassTime";

    public static final String EXTRAPAY = "extraPay";

    public static final String AREA = "area";

    public static final String STATUS = "status";


}
