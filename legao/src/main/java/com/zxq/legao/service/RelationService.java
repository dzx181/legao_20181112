package com.zxq.legao.service;

import com.zxq.legao.entity.po.RelationPO;
import com.zxq.legao.entity.vo.RelationVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RelationService {

    int insertRelation(RelationPO relationPO);

    int deleteRelation(List<Integer> relationIDs);

    int updateRelation(RelationPO relationPO);

    int updateBatchRelation(RelationPO relationPO);

    String selectRelation(Integer page, RelationPO relationPO, HttpServletRequest request);

    List<RelationVO> selectRelationByScheduleID(Integer scheduleID);

    List<RelationVO>selectAllRelation();
}
