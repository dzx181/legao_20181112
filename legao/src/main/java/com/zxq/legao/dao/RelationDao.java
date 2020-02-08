package com.zxq.legao.dao;

import com.zxq.legao.entity.po.RelationPO;
import com.zxq.legao.entity.po.SchedulePO;
import com.zxq.legao.entity.vo.RelationVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RelationDao {
    int insertRelation(RelationPO relationPO);

    int deleteRelation(List<Integer> relationID);

    int updateRelation(RelationPO relationPO);

    int updateBatchRelation(@Param("relationPO") RelationPO relationPO,@Param("caption")Integer caption);

    List<RelationVO> selectRelation(RelationPO relationPO);

    List<RelationVO> selectRelationByScheduleID(Integer scheduleID);

    int insertBatchRelation(@Param("list") List<Integer> studentList, @Param("schedule")SchedulePO schedulePO);

    int selectStuTotalByScheID(Integer scheduleId);

    List<RelationPO> selectStudentNumByschID(Integer scheduleId);

    RelationPO selectRelationByID(Integer realtionID);

}
