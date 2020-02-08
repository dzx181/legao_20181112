package com.zxq.legao.dao;

import com.zxq.legao.entity.po.DepositPO;

import java.util.List;

/**
 * <p>
 * 订金表 Mapper 接口
 * </p>
 *
 * @author dengzhenxiang
 * @since 2019-02-18
 */
public interface DepositDao  {
    int insertDeposit(DepositPO depositPO);

    int deleteDeposit(List<Integer> depositIDs);

    int updateDeposit(DepositPO depositPO);

    List<DepositPO> selectDeposit(DepositPO depositPO);

    DepositPO selectDepositByID(Integer depositID);

    List<DepositPO> findAllDepositName();

    List<DepositPO> selectAllDeposit();
}
