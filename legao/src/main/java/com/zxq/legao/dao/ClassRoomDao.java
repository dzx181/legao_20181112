package com.zxq.legao.dao;


import com.zxq.legao.entity.po.ClassRoomPO;
import com.zxq.legao.entity.vo.ClassRoomVO;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author dengzhenxiang
 * @since 2018-11-27
 */
public interface ClassRoomDao {
    int insertClassRoom(ClassRoomPO classRoomPO);

    int deleteClassRoom(List<Integer> classRoomIDs);

    int updateClassRoom(ClassRoomPO classRoomPO);

    List<ClassRoomPO> selectClassRoom(ClassRoomPO classRoomPO);

    ClassRoomVO selectClassRoomByID(Integer classRoomID);

    List<ClassRoomPO> findAllClassRoomName();
}
