package com.zxq.legao.util;

import com.zxq.legao.entity.po.SchedulePO;

/**
 * 字符串工具
 *
 * @author dengzhenxiang Email:dengzhenxiang@co-mall.com
 * @since 2020/5/2
 */
public class StringUtil {
    /**
     * 判断字段方法
     * @param schedulePO
     * @return
     */
    public static boolean ifNUll(SchedulePO schedulePO){
        if (schedulePO.getSchoolAreaID() == null && schedulePO.getWeekOfYear() == null
                && schedulePO.getTeacherID() == null  && schedulePO.getDateID() == null){
            return true;
        }
        return false;
    }
}
