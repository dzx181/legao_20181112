package com.zxq.legao.util;

import com.zxq.legao.entity.vo.ScheduleVO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Description:
 * <p>
 * 日期工具类
 * </p>
 *
 * @author dengzhenxiang
 * @Date 2018/11/11 17:41
 */
public class DateUtil {

    /**
     * 根据日期计算年龄
     *
     * @param date
     * @return
     */
    public static String getAge(Date date) {
        Long currentTime = System.currentTimeMillis();
        if ((currentTime - date.getTime()) <= 0 || date == null) {
            return "0岁0个月";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(new Date());

        int ageyear = calendar1.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
        int agemonth = calendar1.get(Calendar.MONTH) - calendar.get(Calendar.MONTH);
        if (agemonth <= 0) {
            agemonth = 0;
        }
        String age = ageyear + "岁" + agemonth + "个月";
        return age;

    }

    /**
     *  计算星期,周数
     * @param date
     * @return 集合中第一个元素为课程时间在星期几，第二个元素为在一年里的第几周
     */
    public static List<Integer> getWeek(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        Integer week = calendar.get(Calendar.DAY_OF_WEEK);
        Integer weekYear = calendar.get(Calendar.WEEK_OF_YEAR);
        List<Integer> dateList = new ArrayList<>(2);
        dateList.add(week);
        dateList.add(weekYear);
        return dateList;
    }

    /**
     * 计算当前时间所在的周
     * @param date
     * @return
     */
    public static List<String> getStartAndEndWeekDate(Date date){
        List<String> dateList = new ArrayList<>(2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = calendar.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 获得当前日期是一个星期的第几天
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        // 获取该周第一天
        calendar.add(Calendar.DATE, calendar.getFirstDayOfWeek() - day);
        dateList.add(simpleDateFormat.format(calendar.getTime()));
        // 获取该周最后一天
        calendar.add(Calendar.DATE, 6);
        dateList.add(simpleDateFormat.format(calendar.getTime()));
        return dateList;
    }

    /**
     * 根据某年的周数获取日期范围
     * @param scheduleVO
     */
    public static  List<ScheduleVO> getDatebyWeekOfYear(List<ScheduleVO> scheduleVO){
        if (scheduleVO.isEmpty()){
            return null;
        }
        int size = scheduleVO.size();
        int year = Calendar.getInstance().get((Calendar.YEAR));
        int week = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日");
        for (int i = 0; i <size ; i++) {
            week = Integer.valueOf(scheduleVO.get(i).getWeekOfYear());
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, 0, 1);
            //算出第一周还剩几天 +1是因为1号是1天
            int dayOfWeek = 7- calendar.get(Calendar.DAY_OF_WEEK)+2;
            //周数减去第一周再减去要得到的周
            week -=2;
            calendar.add(Calendar.DAY_OF_YEAR, week*7+dayOfWeek);
            scheduleVO.get(i).setWeekStartTime(simpleDateFormat.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_YEAR, 6);
            scheduleVO.get(i).setWeekEndTime(simpleDateFormat.format(calendar.getTime()));
        }
        return scheduleVO;
}
}
