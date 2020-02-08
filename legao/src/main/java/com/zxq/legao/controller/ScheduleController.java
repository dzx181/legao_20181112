package com.zxq.legao.controller;


import com.zxq.legao.entity.po.SchedulePO;
import com.zxq.legao.entity.vo.ScheduleExportVO;
import com.zxq.legao.entity.vo.ScheduleVO;
import com.zxq.legao.entity.vo.UserVO;
import com.zxq.legao.service.ScheduleService;
import com.zxq.legao.util.ExportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.zxq.legao.constant.ModuleConstant.UPLOADFILE_PATH;
import static com.zxq.legao.constant.ModuleEnum.SCHEDULE;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dengzhenxiang
 * @since 2018-11-27
 */
@Controller
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    /**
     * 文件导出
     * @return
     * @throws IOException
     */
    @RequestMapping("/scheduleExportExcel")
    public ResponseEntity<byte[]> importExcel(@RequestParam("beginTime") @DateTimeFormat(pattern = "yyyy-MM-dd")Date beginTime,
                                              @RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime) throws IOException {
        // 下载文件路径,文件对象,user所有字段值
        String path = UPLOADFILE_PATH+ "排课信息表" + ".xls";
        String[] thTitle = {"学员","课程","年龄段", "上课时间","星期", "学员性别","老师","签到状态","校区"};
        // 获取所有的用户
        List<ScheduleExportVO> allRelation = scheduleService.selectScheduleByCourseTime(beginTime,endTime);
        // 调用导出Excel方法
        ExportUtil.exportFile(thTitle,"排课信息表",allRelation, path,SCHEDULE);
        // 文件下载
        return ExportUtil.download(path, "排课信息表"+ ".xls");
    }
    /**
     * 跳转到ScheduleAdd
     */
    @RequestMapping("/scheduleAdd")
    public String jumpScheduleAdd(HttpServletRequest request) {
        request.setAttribute("type", request.getAttribute("type"));
        return "schedule/scheduleAdd";
    }

    /**
     * 查询排课
     */
    @RequestMapping("/selectSchedule")
    public String selectSchedule(SchedulePO SchedulePO, HttpServletRequest request, Integer page) {
        return scheduleService.selectSchedule(SchedulePO, request);
    }

    /**
     * 添加排课
     */
    @RequestMapping("/insertSchedule")
    public String insertSchedule(SchedulePO schedule, HttpServletRequest request) {
        if (scheduleService.insertSchedule(schedule,request) > 0) {
            request.setAttribute("type", "yes");
        } else {
            request.setAttribute("type", "no");
        }
        List<ScheduleVO> allWeekOfYear = scheduleService.findAllweekOfYear();
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("allWeekOfYear", allWeekOfYear);

        return "schedule/scheduleAdd";
    }

    /**
     * 删除排课
     */
    @RequestMapping("/deleteSchedules")
    public String deleteSchedules(Integer[] caption, HttpServletRequest request) {

        scheduleService.deleteSchedule(Arrays.asList(caption));

        List<ScheduleVO> allWeekOfYear = scheduleService.findAllweekOfYear();
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("allWeekOfYear", allWeekOfYear);
        return "forward:/selectSchedule";


    }

    /**
     * 根据id查找排课
     */
    @RequestMapping("/editSchedule")
    public String editSchedule(@RequestParam("scheduleId") Integer scheduleId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserVO userVO = (UserVO) session.getAttribute("user");
        if (userVO!=null) {
            if (Integer.valueOf(userVO.getStatus()) != 1) {
                request.setAttribute("msg","抱歉，您没有修改权限");
                return "forward:/selectSchedule";
            }
        }
        ScheduleVO scheduleVO = scheduleService.selectScheduleByID(scheduleId);
        request.setAttribute("scheduleByID", scheduleVO);
        return "forward:/editScheduleFrom";

    }

    /**
     * 根据给编辑页赋值
     */
    @RequestMapping("/editScheduleFrom")
    public String editScheduleFrom(HttpServletRequest request) {
        ScheduleVO scheduleVO = (ScheduleVO) request.getAttribute("scheduleByID");
        request.setAttribute("scheduleEdit", scheduleVO);
        return "schedule/scheduleEdit";

    }

    /**
     * 保存编辑值
     */
    @RequestMapping("/saveSchedule")
    public String saveSchedule(SchedulePO Schedule, HttpServletRequest request) {
        scheduleService.updateSchedule(Schedule,request);
        List<ScheduleVO> allWeekOfYear = scheduleService.findAllweekOfYear();
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("allWeekOfYear", allWeekOfYear);
        return "redirect:/selectSchedule";
    }
}

