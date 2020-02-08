package com.zxq.legao.service;

import com.zxq.legao.entity.po.SchoolAreaPO;
import com.zxq.legao.entity.vo.SchoolAreaVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SchoolAreaService {
    int insertSchoolArea(SchoolAreaPO schoolAreaPO);

    int deleteSchoolArea(List<Integer> schoolAreaIDs);

    int updateSchoolArea(SchoolAreaPO schoolAreaPO);

    String selectSchoolArea(Integer page, SchoolAreaPO schoolAreaPO, HttpServletRequest request);

    List<SchoolAreaVO> findAllSchoolAreaName(SchoolAreaPO schoolArea);


    SchoolAreaVO selectSchoolAreaByID(Integer schoolAreaID);

    List<SchoolAreaVO> selectAllSchoolArea();

}
