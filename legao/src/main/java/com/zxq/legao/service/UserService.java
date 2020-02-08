package com.zxq.legao.service;


import com.zxq.legao.entity.po.UserPO;
import com.zxq.legao.entity.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description:
 * <p>
 * 常量类
 * </p>
 *
 * @author dengzhenxiang
 * @Date 2018/11/11 17:41
 */
public interface UserService {

    int insertUser(UserPO userPO);

    int deleteUser(List<Integer> userIDs);

    int updateUser(UserPO userPO);

    String selectUser(Integer page, UserPO userPO, HttpServletRequest request);

    List<UserVO> findUsername(UserPO user);

    boolean isDeleteItself(Integer[] caption, Integer userID);

    UserVO selectUserByID(Integer userID);

    UserVO selectFieldsByUserID(Integer userID);

    UserVO selectEmployFieldsByUserID(Integer userID);

    UserVO selectContractFieldsByUserID(Integer userID);

    UserVO selectUserByNameAndPass(UserPO user);

    List<UserVO> selectAllUser();



}
