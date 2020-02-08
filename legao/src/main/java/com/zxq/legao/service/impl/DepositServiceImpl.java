package com.zxq.legao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxq.legao.dao.DepositDao;
import com.zxq.legao.entity.po.DepositPO;
import com.zxq.legao.service.DepositService;
import com.zxq.legao.constant.ModuleConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 订金表 服务实现类
 * </p>
 *
 * @author dengzhenxiang
 * @since 2019-02-18
 */
@Service
public class DepositServiceImpl implements DepositService {
    @Autowired
    private DepositDao depositDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertDeposit(DepositPO depositPO) {
        return depositDao.insertDeposit(depositPO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteDeposit(List<Integer> depositIDs) {
        return depositDao.deleteDeposit(depositIDs);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateDeposit(DepositPO depositPO) {
        return depositDao.updateDeposit(depositPO);
    }

    @Override
    public String selectDeposit(Integer page, DepositPO depositPO, HttpServletRequest request) {
        //模糊查询保留值
        if (depositPO != null) {
            request.setAttribute("blurDeposit", depositPO);
        }
        if (page == null) {
            page = 0;
        }
        //page为初始页，pageSize表一页显示多少条
        PageHelper.startPage(page, ModuleConstant.PAGESIZE);
        List<DepositPO> list = depositDao.selectDeposit(depositPO);

        PageInfo pageInfo = new PageInfo(list);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("depositVOList", list);
        return "deposit/depositList";
    }


    @Override
    public DepositPO selectDepositByID(Integer depositID) {
        return depositDao.selectDepositByID(depositID);
    }

    @Override
    public List<DepositPO> findAllDepositName() {
        return depositDao.findAllDepositName();
    }

    @Override
    public List<DepositPO> selectAllDeposit() {
        return depositDao.selectAllDeposit();
    }
}
