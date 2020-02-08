package com.zxq.legao.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxq.legao.dao.UserDao;
import com.zxq.legao.entity.po.UserPO;
import com.zxq.legao.entity.vo.UserVO;
import com.zxq.legao.service.UserService;
import com.zxq.legao.constant.ModuleConstant;
import com.zxq.legao.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description:
 * <p>
 * 用户业务层
 * </p>
 *
 * @author dengzhenxiang
 * @Date 2018/11/11 17:41
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;



    /**
     * 添加用户
     *
     * @param userPO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertUser(UserPO userPO) {
        if (userPO == null) {
            return 0;
        } else {
            //加密
            String saltDB = ModuleConstant.MD5_SALT;
            userPO.setPassword(MD5Util.inputPassToDBpass(userPO.getPassword(), saltDB));
            return userDao.insertUser(userPO);
        }

    }

    /**
     * 批量删除用户
     *
     * @param userIDs
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteUser(List<Integer> userIDs) {
        return userDao.deleteUser(userIDs);
    }

    /**
     * 更新用户
     *
     * @param userPO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUser(UserPO userPO) {
        if (userPO == null) {
            return 0;
        } else {
            //加密
            String saltDB = ModuleConstant.MD5_SALT;
            userPO.setPassword(MD5Util.inputPassToDBpass(userPO.getPassword(), saltDB));
            return userDao.updateUser(userPO);
        }

    }

    /**
     * 分页查询用户
     *
     * @param page
     * @param userPO
     * @param request
     * @return
     */
    @Override
    public String selectUser(Integer page, UserPO userPO, HttpServletRequest request) {
        //模糊查询保留值
        if (userPO != null) {
            request.setAttribute("blurUser", userPO);
        }
        if (page == null) {
            page = 0;
        }
        PageHelper.startPage(page, ModuleConstant.PAGESIZE);
        List<UserVO> list = userDao.selectUser(userPO);
        PageInfo pageInfo = new PageInfo(list);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("userVOList", list);
        return "user/userList";
    }

    /**
     * 查找用户名
     *
     * @param userPO
     * @return
     */
    @Override
    public List<UserVO> findUsername(UserPO userPO) {
        if (userPO == null) {
            return null;
        } else {
            return userDao.findUsername(userPO);
        }

    }

    /**
     * 判断是否删除正在登录的用户（自己）
     *
     * @param caption
     * @param userID
     * @return
     */
    @Override
    public boolean isDeleteItself(Integer[] caption, Integer userID) {
        boolean flag = false;
        for (int i = 0; i < caption.length; i++) {
            if (caption[i].equals(userID)) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 根据用户id查找用户
     *
     * @param userID
     * @return
     */
    @Override
    public UserVO selectUserByID(Integer userID) {
        return userDao.selectUserByID(userID);
    }

    /**
     * 根据用户id查找用户选择展示的学生字段
     *
     * @param userID
     * @return
     */
    @Override
    public UserVO selectFieldsByUserID(Integer userID) {
        return userDao.selectFieldsByUserID(userID);
    }

    /**
     * 根据用户id查找用户选择展示的员工字段
     *
     * @param userID
     * @return
     */
    @Override
    public UserVO selectEmployFieldsByUserID(Integer userID) {
        return userDao.selectEmployFieldsByUserID(userID);
    }

    @Override
    public UserVO selectContractFieldsByUserID(Integer userID) {
        return userDao.selectContractFieldsByUserID(userID);
    }

    /**
     * 根据用户名和登录密码查找是否存在该用户
     *
     * @param userPO
     * @return
     */
    @Override
    public UserVO selectUserByNameAndPass(UserPO userPO) {
        if (userPO == null) {
            return null;
        } else {
            String saltDB = ModuleConstant.MD5_SALT;
            userPO.setPassword(MD5Util.inputPassToDBpass(userPO.getPassword(), saltDB));
            return userDao.selectUserByNameAndPass(userPO);
        }
    }

    @Override
    public List<UserVO> selectAllUser() {
        return userDao.selectUser(new UserPO());
    }
}
