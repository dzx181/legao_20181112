package com.zxq.legao.dao;

import com.zxq.legao.entity.po.ClasstimepackPO;

import java.util.List;

/**
 * <p>
 * 课时包表 Mapper 接口
 * </p>
 *
 * @author dengzhenxiang
 * @since 2019-02-18
 */
public interface ClasstimepackDao  {
    int insertClasstimepack(ClasstimepackPO classtimepackPO);

    int deleteClasstimepack(List<Integer> classtimepackIDs);

    int updateClasstimepack(ClasstimepackPO classtimepackPO);

    List<ClasstimepackPO> selectClasstimepack(ClasstimepackPO classtimepackPO);

    ClasstimepackPO selectClasstimepackByID(Integer classtimepackID);

    List<ClasstimepackPO> findAllClasstimepackName();

    List<ClasstimepackPO> selectAllClassTimePack();

}
