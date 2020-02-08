package com.zxq.legao.dao;

import com.zxq.legao.entity.po.CoursePO;
import com.zxq.legao.entity.vo.CourseVO;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author dengzhenxiang
 * @since 2018-11-27
 */
public interface CourseDao {
    int insertCourse(CoursePO coursePO);

    int deleteCourse(List<Integer> courseIDs);

    int updateCourse(CoursePO coursePO);

    List<CourseVO> selectCourse(CoursePO coursePO);

    CourseVO selectCourseByID(Integer courseID);

    List<CoursePO> findAllCourseName();

    List<CourseVO> selectCourseBySeriesId(Integer seriesId);
}
