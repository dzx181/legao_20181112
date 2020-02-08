package com.zxq.legao.controller;


import com.alibaba.fastjson.JSON;
import com.zxq.legao.entity.po.CoursePO;
import com.zxq.legao.entity.vo.CourseVO;
import com.zxq.legao.service.CourseService;
import com.zxq.legao.util.ExportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.zxq.legao.constant.ModuleConstant.UPLOADFILE_PATH;
import static com.zxq.legao.constant.ModuleEnum.COURSE;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dengzhenxiang
 * @since 2018-11-27
 */
@Controller

public class CourseController {
    @Autowired
    private CourseService courseService;
    /**
     * 文件导出
     * @return
     * @throws IOException
     */
    @RequestMapping("/courseExportExcel")
    public ResponseEntity<byte[]> importExcel() throws IOException {
        // 下载文件路径,文件对象,user所有字段值
        String path = UPLOADFILE_PATH+ "课程信息表" + ".xls";
        String[] thTitle = {"课程id","课程名称", "年龄段", "教具", "课时", "课程系列"};
        // 获取所有的用户
        List<CourseVO> allCourse = courseService.selectAllCourse();
        // 调用导出Excel方法
        ExportUtil.exportFile(thTitle,"课程信息表",allCourse, path,COURSE);
        // 文件下载
        return ExportUtil.download(path, "课程信息表"+ ".xls");
    }
    /**
     * 跳转到courseAdd
     */
    @RequestMapping("/courseAdd")
    public String jumpCourseAdd(HttpServletRequest request) {
        request.setAttribute("type", request.getAttribute("type"));
        return "course/courseAdd";
    }

    /**
     * 查询课程
     */
    @RequestMapping("/selectCourse")
    public String selectCourse(CoursePO coursePO, HttpServletRequest request, Integer page) {

        return courseService.selectCourse(page, coursePO, request);
    }

    /**
     * 添加课程
     */
    @RequestMapping("/insertCourse")
    public String insertCourse(CoursePO course, HttpServletRequest request) {
        if (courseService.insertCourse(course) > 0) {
            request.setAttribute("type", "yes");
        } else {
            request.setAttribute("type", "no");
        }
        ServletContext servletContext = request.getServletContext();
        List<CoursePO> allCourseName = courseService.findAllCourseName();
        servletContext.setAttribute("allCourseName", allCourseName);
        return "course/courseAdd";
    }

    /**
     * 删除课程
     */
    @RequestMapping("/deleteCourses")
    public String deleteCourses(Integer[] caption, HttpServletRequest request) {

        courseService.deleteCourse(Arrays.asList(caption));
        ServletContext servletContext = request.getServletContext();
        List<CoursePO> allCourseName = courseService.findAllCourseName();
        servletContext.setAttribute("allCourseName", allCourseName);
        return "forward:/selectCourse";

    }

    /**
     * 根据id查找课程
     */
    @RequestMapping("/editCourse")
    public String editCourse(@RequestParam("courseId") Integer courseId, HttpServletRequest request) {
        CourseVO courseVO = courseService.selectCourseByID(courseId);
        request.setAttribute("courseByID", courseVO);
        return "forward:/editCourseFrom";

    }

    /**
     * 根据给编辑页赋值
     */
    @RequestMapping("/editCourseFrom")
    public String editCourseFrom(HttpServletRequest request) {
        CourseVO courseVO = (CourseVO) request.getAttribute("courseByID");
        request.setAttribute("courseEdit", courseVO);
        return "course/courseEdit";

    }

    /**
     * 保存编辑值
     */
    @RequestMapping("/saveCourse")
    public String saveCourse(CoursePO course, HttpServletRequest request) {
        courseService.updateCourse(course);
        ServletContext servletContext = request.getServletContext();
        List<CoursePO> allCourseName = courseService.findAllCourseName();
        servletContext.setAttribute("allCourseName", allCourseName);
        return "redirect:/selectCourse";

    }

    /**
     * 根据seriesId找课程
     */

    @RequestMapping("/selectCourseBySeriesId")
    @ResponseBody
    public String selectCourseBySeriesId(@RequestBody CoursePO coursePO) {
        String courseStr = null;
        List<CourseVO> courseVOList = courseService.selectCourseBySeriesId(coursePO.getSeriesID());
        if (courseVOList.size() > 0) {

             courseStr = JSON.toJSONString(courseVOList);
            return courseStr;
        } else {
             courseStr = JSON.toJSONString("");
            return courseStr;
        }

    }
}

