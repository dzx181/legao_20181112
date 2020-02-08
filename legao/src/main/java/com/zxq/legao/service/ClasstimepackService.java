package com.zxq.legao.service;

import com.zxq.legao.entity.po.ClasstimepackPO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 课时包表 服务类
 * </p>
 *
 * @author dengzhenxiang
 * @since 2019-02-18
 */
public interface ClasstimepackService  {
    int insertClasstimepack(ClasstimepackPO classtimepackPO);

    int deleteClasstimepack(List<Integer> classtimepackIDs);

    int updateClasstimepack(ClasstimepackPO classtimepackPO);

    String selectClasstimepack(Integer page, ClasstimepackPO classtimepackPO, HttpServletRequest request);

    ClasstimepackPO selectClasstimepackByID(Integer classtimepackID);

    List<ClasstimepackPO> findAllClasstimepackName();

    List<ClasstimepackPO> selectAllClassTimePack();
}
