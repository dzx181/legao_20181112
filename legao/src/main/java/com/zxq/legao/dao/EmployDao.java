package com.zxq.legao.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;

import com.zxq.legao.entity.po.EmployPO;

import com.zxq.legao.entity.vo.EmployVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author dengzhenxiang
 * @since 2018-11-27
 */
public interface EmployDao extends BaseMapper<EmployPO> {

    List<EmployVO> selectEmploy(@Param("employPO") EmployPO studentPO, @Param("fields") List<String> fields);

    List<EmployPO> selectAllEmploy();

    List<EmployVO> selectAllTeacherName();

    EmployVO selectEmployById(Integer employId);

    int updateAllClassTime(EmployVO employVO);

    List<EmployVO> selectAllEmploys();
}
