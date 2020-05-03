package com.zxq.legao.service;


import com.zxq.legao.entity.dto.ScheduleDTO;
import com.zxq.legao.entity.po.SchedulePO;
import com.zxq.legao.entity.vo.ScheduleExportVO;
import com.zxq.legao.entity.vo.ScheduleVO;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author dengzhenxiang
 * @since 2018-11-27
 */
public interface ScheduleService {
    int insertSchedule(SchedulePO schedulePO,HttpServletRequest request);

    int deleteSchedule(List<Integer> scheduleIDs);

    int updateSchedule(SchedulePO schedulePO,HttpServletRequest request);

    String selectSchedule(ScheduleDTO schedulePO, HttpServletRequest request);

    ScheduleVO selectScheduleByID(Integer scheduleID);

    List<ScheduleVO> findAllweekOfYear();

    List<ScheduleExportVO> selectScheduleByCourseTime(Date beginTime, Date endTime);

}
