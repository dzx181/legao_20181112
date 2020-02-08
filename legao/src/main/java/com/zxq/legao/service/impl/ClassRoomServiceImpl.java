package com.zxq.legao.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxq.legao.dao.ClassRoomDao;
import com.zxq.legao.entity.po.ClassRoomPO;
import com.zxq.legao.entity.vo.ClassRoomVO;
import com.zxq.legao.service.ClassRoomService;
import com.zxq.legao.constant.ModuleConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dengzhenxiang
 * @since 2018-11-27
 */
@Service
public class ClassRoomServiceImpl implements ClassRoomService {
    @Autowired
    private ClassRoomDao classRoomDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertClassRoom(ClassRoomPO classRoomPO) {
        return classRoomDao.insertClassRoom(classRoomPO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteClassRoom(List<Integer> classRoomIDs) {
        return classRoomDao.deleteClassRoom(classRoomIDs);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateClassRoom(ClassRoomPO classRoomPO) {
        return classRoomDao.updateClassRoom(classRoomPO);
    }

    @Override
    public String selectClassRoom(Integer page, ClassRoomPO classRoomPO, HttpServletRequest request) {
        //模糊查询保留值
        if (classRoomPO != null) {
            request.setAttribute("blurClassRoom", classRoomPO);
        }
        if (page == null) {
            page = 0;
        }
        //page为初始页，pageSize表一页显示多少条
        PageHelper.startPage(page, ModuleConstant.PAGESIZE);
        List<ClassRoomPO> list = classRoomDao.selectClassRoom(classRoomPO);

        PageInfo pageInfo = new PageInfo(list);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("classRoomVOList", list);
        return "classRoom/classRoomList";
    }


    @Override
    public ClassRoomVO selectClassRoomByID(Integer classRoomID) {
        return classRoomDao.selectClassRoomByID(classRoomID);
    }

    @Override
    public List<ClassRoomPO> findAllClassRoomName() {
        return classRoomDao.findAllClassRoomName();
    }

    @Override
    public List<ClassRoomPO> selectAllClassRoom() {
        return classRoomDao.selectClassRoom(null);
    }
}
