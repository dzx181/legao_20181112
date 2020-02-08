package com.zxq.legao.dao;

import com.zxq.legao.entity.po.MembercardPO;

import java.util.List;

/**
 * <p>
 * 会员卡表 Mapper 接口
 * </p>
 *
 * @author dengzhenxiang
 * @since 2019-02-18
 */
public interface MembercardDao {
    int insertMembercard(MembercardPO classtimepackPO);

    int deleteMembercard(List<Integer> classtimepackIDs);

    int updateMembercard(MembercardPO classtimepackPO);

    List<MembercardPO> selectMembercard(MembercardPO classtimepackPO);

    MembercardPO selectMembercardByID(Integer classtimepackID);

    List<MembercardPO> findAllMembercardName();
}
