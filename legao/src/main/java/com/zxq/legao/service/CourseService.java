package com.zxq.legao.service;

import com.zxq.legao.entity.po.CoursePO;
import com.zxq.legao.entity.vo.CourseVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author dengzhenxiang
 * @since 2018-11-27
 */
public interface CourseService {
    int insertCourse(CoursePO coursePO);

    int deleteCourse(List<Integer> courseIDs);

    int updateCourse(CoursePO coursePO);

    String selectCourse(Integer page, CoursePO courseO, HttpServletRequest request);

    CourseVO selectCourseByID(Integer courseID);

    List<CoursePO> findAllCourseName();

    List<CourseVO> selectCourseBySeriesId(Integer seriesId);

    List<CourseVO> selectAllCourse();

}
