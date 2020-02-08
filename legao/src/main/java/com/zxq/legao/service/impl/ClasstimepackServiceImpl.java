package com.zxq.legao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxq.legao.constant.ModuleConstant;
import com.zxq.legao.dao.ClasstimepackDao;
import com.zxq.legao.entity.po.ClasstimepackPO;
import com.zxq.legao.service.ClasstimepackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 课时包表 服务实现类
 * </p>
 *
 * @author dengzhenxiang
 * @since 2019-02-18
 */
@Service
public class ClasstimepackServiceImpl  implements ClasstimepackService {
    @Autowired
    private ClasstimepackDao classtimepackDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertClasstimepack(ClasstimepackPO classtimepackPO) {
        return classtimepackDao.insertClasstimepack(classtimepackPO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteClasstimepack(List<Integer> classtimepackIDs) {
        return classtimepackDao.deleteClasstimepack(classtimepackIDs);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateClasstimepack(ClasstimepackPO classtimepackPO) {
        return classtimepackDao.updateClasstimepack(classtimepackPO);
    }

    @Override
    public String selectClasstimepack(Integer page, ClasstimepackPO classtimepackPO, HttpServletRequest request) {
        //模糊查询保留值
        if (classtimepackPO != null) {
            request.setAttribute("blurClasstimepack", classtimepackPO);
        }
        if (page == null) {
            page = 0;
        }
        //page为初始页，pageSize表一页显示多少条
        PageHelper.startPage(page, ModuleConstant.PAGESIZE);
        List<ClasstimepackPO> list = classtimepackDao.selectClasstimepack(classtimepackPO);

        PageInfo pageInfo = new PageInfo(list);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("classtimepackVOList", list);
        return "classtimepack/classtimepackList";
    }


    @Override
    public ClasstimepackPO selectClasstimepackByID(Integer classtimepackID) {
        return classtimepackDao.selectClasstimepackByID(classtimepackID);
    }

    @Override
    public List<ClasstimepackPO> findAllClasstimepackName() {
        return classtimepackDao.findAllClasstimepackName();
    }

    @Override
    public List<ClasstimepackPO> selectAllClassTimePack() {
        return classtimepackDao.selectAllClassTimePack();
    }
}
