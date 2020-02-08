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
 * 课时包表
 * </p>
 *
 * @author dengzhenxiang
 * @since 2019-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("legao_classtimepack")
public class ClasstimepackPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 姓名
     */
    @TableField("name")
    private String name;
    /**
     * 课时数
     */
    @TableField("classTime")
    private String classTime;
    /**
     * 价格
     */
    @TableField("price")
    private String price;
    /**
     * 优先级
     */
    @TableField("priority")
    private Integer priority;
    /**
     * 备注
     */
    @TableField("remark")
    private String remark;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String CLASSTIME = "classTime";

    public static final String PRICE = "price";

    public static final String PRIORITY = "priority";

    public static final String REMARK = "remark";

}
