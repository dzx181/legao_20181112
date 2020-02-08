package com.zxq.legao.service;

import com.zxq.legao.entity.po.FollowPO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface FollowService {
    int insertFollow(FollowPO followPO);

    int deleteFollow(List<Integer> followIDs);

    int updateFollow(FollowPO followPO);

    String selectFollow(Integer page, FollowPO followPO, HttpServletRequest request);

    FollowPO selectFollowByID(Integer followID);

    List<FollowPO> findAllFollowName();

    List<FollowPO> selectAllFollow();
}
