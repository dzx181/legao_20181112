package com.zxq.legao.service.impl;


import com.zxq.legao.common.ComputeCommon;
import com.zxq.legao.dao.RelationDao;
import com.zxq.legao.dao.ScheduleDao;
import com.zxq.legao.dao.StudentDao;
import com.zxq.legao.entity.dto.ScheduleDTO;
import com.zxq.legao.entity.po.RelationPO;
import com.zxq.legao.entity.po.SchedulePO;
import com.zxq.legao.entity.po.StudentPO;
import com.zxq.legao.entity.vo.*;
import com.zxq.legao.service.ScheduleService;
import com.zxq.legao.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dengzhenxiang
 * @since 2018-11-27
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleDao scheduleDao;
    @Autowired
    private RelationDao relationDao;
    @Autowired
    private StudentDao studentDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSchedule(SchedulePO schedulePO, HttpServletRequest request) {
        //计算星期
        ComputeCommon.computeWeek(schedulePO);
        schedulePO.setWeekOfYearSpecial(Calendar.getInstance().get(Calendar.YEAR)+schedulePO.getWeekOfYear());
        //添加排课的同时，添加签到信息
        int flag = scheduleDao.insertSchedule(schedulePO);
        UserVO userVO = (UserVO) request.getSession().getAttribute("user");
        schedulePO.setModifyPerson(userVO.getUsername());
        relationDao.insertBatchRelation(schedulePO.getStudentList(), schedulePO);
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteSchedule(List<Integer> scheduleIDs) {
        return scheduleDao.deleteSchedule(scheduleIDs);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateSchedule(SchedulePO schedulePO, HttpServletRequest request) {
        //更改签到表
        if (schedulePO.getStudentList() != null) {
            List<RelationVO> relationVOList = relationDao.selectRelationByScheduleID(schedulePO.getId());
            List<Integer> sudentList = new ArrayList<>();
            if (!relationVOList.isEmpty()) {
                for (RelationVO re : relationVOList) {
                    sudentList.add(re.getId());
                }
                relationDao.deleteRelation(sudentList);
                UserVO userVO = (UserVO) request.getSession().getAttribute("user");
                schedulePO.setModifyPerson(userVO.getUsername());
                relationDao.insertBatchRelation(schedulePO.getStudentList(), schedulePO);
            }
        }
        ComputeCommon.computeWeek(schedulePO);
        schedulePO.setWeekOfYearSpecial(Calendar.getInstance().get(Calendar.YEAR)+schedulePO.getWeekOfYear());
        return scheduleDao.updateSchedule(schedulePO);
    }

    @Override
    public String selectSchedule(ScheduleDTO schedulePO, HttpServletRequest request) {
        //模糊查询保留值
        if ((schedulePO.getWeekOfYear() == null) || (schedulePO.getWeekOfYear().equals(""))) {
            Calendar calendar = Calendar.getInstance();
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            schedulePO.setWeekOfYearSpecial(calendar.get(Calendar.YEAR) + "" + calendar.get(Calendar.WEEK_OF_YEAR));
            schedulePO.setThisYear(calendar.get(Calendar.YEAR)+"" );
        }
        if (schedulePO.getWeekOfYear().length() > 4) {
            ScheduleVO scheduleVO = new ScheduleVO();
            BeanUtils.copyProperties(schedulePO,scheduleVO);
            request.setAttribute("blurSchedule", scheduleVO);
        }

        List<ScheduleVO> scheduleVOList = scheduleDao.selectSchedule(schedulePO);
        DateVO dateVO = new DateVO();
        if (scheduleVOList.size() != 0) {
            //取第一条设置本周的起始时间
            ScheduleVO scheduleVO = scheduleVOList.get(0);
            List<String> startAndEndWeekDateList = DateUtil.getStartAndEndWeekDate(scheduleVO.getCourseDate());
            String startWeekDate = startAndEndWeekDateList.get(0);
            String endWeekDate = startAndEndWeekDateList.get(1);
            dateVO.setWeekOfYear(scheduleVO.getWeekOfYear());
            dateVO.setStartWeekDate(startWeekDate);
            dateVO.setEndWeekDate(endWeekDate);

            //得出每个课程的总人数，方案有待改善
            int scheListSize = scheduleVOList.size();

            for (int i = 0; i < scheListSize; i++) {
                ScheduleVO scheduleVOs = scheduleVOList.get(i);
                int totalCount = relationDao.selectStuTotalByScheID(scheduleVOs.getId());
                scheduleVOs.setTotalStu(totalCount);
                //查询排课对应的每个学生
                List<RelationPO> relationPOlist = relationDao.selectStudentNumByschID(scheduleVOs.getId());
                StringBuilder stringBuilder = new StringBuilder();
                if (!relationPOlist.isEmpty()) {
                    for (RelationPO relation : relationPOlist) {
                        StudentPO studentPO = studentDao.selectStudentNameByID(relation.getStudentID());
                        if (studentPO == null) {
                            continue;
                        }
                        stringBuilder.append(studentPO.getName() + ",");
                    }
                    scheduleVOList.get(i).setStudengNames(stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString());
                }
            }


        }
        request.setAttribute("scheduleVOList", scheduleVOList);
        dateVO.setThisYear((schedulePO.getThisYear() == null) ? Calendar.getInstance().get(Calendar.YEAR) + "" : schedulePO.getThisYear());
        request.setAttribute("dateVO", dateVO);
        return "schedule/scheduleList";
    }

    @Override
    public ScheduleVO selectScheduleByID(Integer scheduleID) {
        return scheduleDao.selectScheduleByID(scheduleID);
    }

    @Override
    public List<ScheduleVO> findAllweekOfYear() {
        return scheduleDao.findAllweekOfYear();
    }

    @Override
    public List<ScheduleExportVO> selectScheduleByCourseTime(Date beginTime, Date endTime) {
        //查询排课信息
        List<ScheduleVO> scheduleVOS = scheduleDao.selectScheduleByCoureTime(beginTime, endTime);
        //结果集合
        List<ScheduleExportVO> scheduleExportVOS = new ArrayList<>(10);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (scheduleVOS != null && !scheduleVOS.isEmpty()) {
            for (ScheduleVO scheduleVO : scheduleVOS) {
                //查询关联表
                List<RelationPO> relationPOList = relationDao.selectStudentNumByschID(scheduleVO.getId());
                if (relationPOList != null && !relationPOList.isEmpty()) {
                    for (RelationPO relation : relationPOList) {
                        //查询学生
                        StudentPO studentPO = studentDao.selectStudentNameByID(relation.getStudentID());
                        //设置结果对象
                        ScheduleExportVO scheduleExportVO = new ScheduleExportVO();
                        scheduleExportVO.setAgeArea(scheduleVO.getCourse() != null ? scheduleVO.getCourse().getAgeArea() : "无")
                                .setClassRoom(scheduleVO.getClassroom() != null ? scheduleVO.getClassroom().getName() : "无")
                                .setCourseTime(format.format(scheduleVO.getCourseDate()) + " " + (scheduleVO.getDate() != null ? scheduleVO.getDate().getTimeSection() : "无上课时间段"))
                                .setSchoolArea(scheduleVO.getSchoolArea() != null ? scheduleVO.getSchoolArea().getName() : "无校区")
                                .setSex(studentPO != null ? studentPO.getSex() : "无")
                                .setTeacherName(scheduleVO.getTeacherVO() != null ? scheduleVO.getTeacherVO().getName() : "无")
                                .setWeek(scheduleVO.getCourseWeek())
                                .setStudentName(studentPO != null ? studentPO.getName() : "无")
                                .setCourseName(scheduleVO.getCourse() != null ? scheduleVO.getCourse().getName() : "无");
                        if (relation.getSignInStatus().equals(1)) {
                            scheduleExportVO.setSignStatus("准时");
                        } else if (relation.getSignInStatus().equals(2)) {
                            scheduleExportVO.setSignStatus("未签到");
                        } else {
                            scheduleExportVO.setSignStatus("请假");
                        }
                        scheduleExportVOS.add(scheduleExportVO);
                    }
                }
            }
        }
        return scheduleExportVOS;
    }
}
