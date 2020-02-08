package com.zxq.legao.service;


import com.baomidou.mybatisplus.service.IService;
import com.zxq.legao.entity.po.EmployPO;
import com.zxq.legao.entity.vo.EmployVO;

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
public interface EmployService extends IService<EmployPO> {
    int insertEmploy(EmployPO employPO);

    int deleteEmploy(List<Integer> employIDs);

    int updateEmploy(EmployPO employPO);

    String selectEmploy(Integer page, EmployPO employPO, HttpServletRequest request);

    EmployPO selectEmployByID(Integer employID);

    List<EmployPO> selectAllEmploy();

    List<EmployVO> selectAllTeacherName();

    int updateAllClassTime(EmployVO employVO);

    List<EmployVO> selectAllEmploys();

}
