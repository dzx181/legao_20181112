package com.zxq.legao.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxq.legao.dao.EmployDao;
import com.zxq.legao.dao.UserDao;
import com.zxq.legao.entity.po.EmployPO;
import com.zxq.legao.entity.vo.EmployVO;
import com.zxq.legao.entity.vo.UserVO;
import com.zxq.legao.service.EmployService;
import com.zxq.legao.constant.ModuleConstant;
import com.zxq.legao.util.ConverstUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
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
public class EmployServiceImpl extends ServiceImpl<EmployDao, EmployPO> implements EmployService {
    @Autowired
    private EmployDao employDao;
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertEmploy(EmployPO employPO) {

        return employDao.insert(employPO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteEmploy(List<Integer> employIDs) {
        return employDao.deleteBatchIds(employIDs);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateEmploy(EmployPO employPO) {
        return employDao.updateById(employPO);
    }

    @Override
    public String selectEmploy(Integer page, EmployPO employPO, HttpServletRequest request) {
        //模糊查询保留值
        if (employPO != null) {
            request.setAttribute("blurEmploy", employPO);
        }
        if (page == null) {
            page = 0;
        }

        //查询当前登录用户选择展示的字段
        HttpSession session = request.getSession();
        UserVO userVO = (UserVO) session.getAttribute("user");
        UserVO userVO1 = userDao.selectEmployFieldsByUserID(userVO.getId());
        String fields = "";
        if (userVO1 == null){
            fields = null;
        }else {
            fields = userDao.selectEmployFieldsByUserID(userVO.getId()).getSelectEmployFields();
        }
        List<EmployVO> list = null;
        List<String> defaultEmployFieldsDB = Arrays.asList(ModuleConstant.DEFAULT_EMPLOY_FIELDS_DB);
        List<String> defaultEmployFieldsZH = Arrays.asList(ModuleConstant.DEFAULT_EMPLOY_FIELDS_ZH);
        List<String> employFieldsDB = ConverstUtil.stringToList(fields, ModuleConstant.EMPLOY_FIELDS_DB);
        List<String> employFieldsZH = ConverstUtil.stringToList(fields, ModuleConstant.EMPLOY_FIELDS_ZH);
        //page为初始页，pageSize表一页显示多少条
        PageHelper.startPage(page, ModuleConstant.PAGESIZE);
        if (fields == null) {
            list = employDao.selectEmploy(employPO, defaultEmployFieldsDB);
            request.setAttribute("FieldZH", defaultEmployFieldsZH);
        } else {
            list = employDao.selectEmploy(employPO, employFieldsDB);
//			if (list.get(0).getBirthday() != null){employFieldsZH.add("年龄");}
            request.setAttribute("FieldZH", employFieldsZH);
        }

        //给页面赋值
        PageInfo pageInfo = new PageInfo(list);
        request.setAttribute("employVOList", list);
        request.setAttribute("pageInfo", pageInfo);
        return "employ/employList";
    }


    @Override
    public EmployPO selectEmployByID(Integer employID) {
        return employDao.selectById(employID);
    }

    @Override
    public List<EmployPO> selectAllEmploy() {
        return employDao.selectAllEmploy();
    }

    @Override
    public List<EmployVO> selectAllTeacherName() {
        return employDao.selectAllTeacherName();
    }

    @Override
    public int updateAllClassTime(EmployVO employVO) {
        return employDao.updateAllClassTime(employVO);
    }

    @Override
    public List<EmployVO> selectAllEmploys() {
        return employDao.selectAllEmploys();
    }

}
