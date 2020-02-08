package com.zxq.legao.service;

import com.zxq.legao.entity.po.MembercardPO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 会员卡表 服务类
 * </p>
 *
 * @author dengzhenxiang
 * @since 2019-02-18
 */
public interface MembercardService {
    int insertMembercard(MembercardPO membercardPO);

    int deleteMembercard(List<Integer> membercardIDs);

    int updateMembercard(MembercardPO membercardPO);

    String selectMembercard(Integer page, MembercardPO membercardPO, HttpServletRequest request);

    MembercardPO selectMembercardByID(Integer membercardID);

    List<MembercardPO> findAllMembercardName();

}
