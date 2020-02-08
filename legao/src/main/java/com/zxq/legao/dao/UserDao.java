package com.zxq.legao.dao;


import com.zxq.legao.entity.po.UserPO;
import com.zxq.legao.entity.vo.UserVO;


import java.util.List;

public interface UserDao {
    int insertUser(UserPO userPO);

    int deleteUser(List<Integer> userIDs);

    int updateUser(UserPO userPO);

    List<UserVO> selectUser(UserPO userPO);

    UserVO selectUserByID(UserPO userPO);

    List<UserVO> findUsername(UserPO user);

    UserVO selectUserByID(Integer userID);

    UserVO selectFieldsByUserID(Integer userID);

    UserVO selectEmployFieldsByUserID(Integer userID);

    UserVO selectContractFieldsByUserID(Integer userID);

    UserVO selectUserByNameAndPass(UserPO user);
}
