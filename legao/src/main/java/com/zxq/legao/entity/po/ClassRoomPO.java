package com.zxq.legao.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
/**
 * Description:
 * <p>
 *     教室
 * </p>
 * @author dengzhenxiang
 * @Date 2018/12/28 22:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ClassRoomPO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 教室名
     */
    private String name;

    /**
     * 年龄段
     */
    private String ageArea;

    /**
     * 负责人
     */
    private String responsePersonName;

}
