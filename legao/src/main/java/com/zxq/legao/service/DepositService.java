package com.zxq.legao.service;

import com.zxq.legao.entity.po.DepositPO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 订金表 服务类
 * </p>
 *
 * @author dengzhenxiang
 * @since 2019-02-18
 */
public interface DepositService  {
    int insertDeposit(DepositPO depositPO);

    int deleteDeposit(List<Integer> depositIDs);

    int updateDeposit(DepositPO depositPO);

    String selectDeposit(Integer page, DepositPO depositPO, HttpServletRequest request);

    DepositPO selectDepositByID(Integer depositID);

    List<DepositPO> findAllDepositName();

    List<DepositPO> selectAllDeposit();
}
