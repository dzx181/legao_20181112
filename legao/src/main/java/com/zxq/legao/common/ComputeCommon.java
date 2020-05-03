package com.zxq.legao.common;

import com.zxq.legao.entity.po.SchedulePO;
import com.zxq.legao.util.DateUtil;

import java.util.Calendar;
import java.util.List;

/**
 * Description:
 * <p>
 * 抽取的公共方法
 * </p>
 *
 * @author dengzhenxiang
 * @Date 2019/1/2 17:59
 */

public class ComputeCommon {
    /**
     * 计算星期
     */
    public static SchedulePO computeWeek(SchedulePO schedulePO) {
        List<Integer> dateList = DateUtil.getWeek(schedulePO.getCourseDate());
        schedulePO.setCourseWeek(String.valueOf(dateList.get(0)));
        schedulePO.setWeekOfYear(Calendar.getInstance().get(Calendar.YEAR)+String.valueOf(dateList.get(1)));
        return schedulePO;
    }

}
