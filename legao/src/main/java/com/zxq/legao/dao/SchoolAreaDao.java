package com.zxq.legao.dao;

import com.zxq.legao.entity.po.SchoolAreaPO;
import com.zxq.legao.entity.vo.SchoolAreaVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SchoolAreaDao {
    int insertSchoolArea(SchoolAreaPO schoolAreaPO);

    int deleteSchoolArea(List<Integer> schoolAreaIDs);

    int updateSchoolArea(SchoolAreaPO schoolAreaPO);

    List<SchoolAreaVO> selectSchoolArea(SchoolAreaPO schoolAreaPO);

    SchoolAreaVO selectSchoolAreaByID(Integer schoolAreaID);
}
