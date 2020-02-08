package com.zxq.legao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxq.legao.dao.MembercardDao;
import com.zxq.legao.entity.po.MembercardPO;
import com.zxq.legao.service.MembercardService;
import com.zxq.legao.constant.ModuleConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 会员卡表 服务实现类
 * </p>
 *
 * @author dengzhenxiang
 * @since 2019-02-18
 */
@Service
public class MembercardServiceImpl implements MembercardService {
    @Autowired
    private MembercardDao membercardDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertMembercard(MembercardPO membercardPO) {
        return membercardDao.insertMembercard(membercardPO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteMembercard(List<Integer> membercardIDs) {
        return membercardDao.deleteMembercard(membercardIDs);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateMembercard(MembercardPO membercardPO) {
        return membercardDao.updateMembercard(membercardPO);
    }

    @Override
    public String selectMembercard(Integer page, MembercardPO membercardPO, HttpServletRequest request) {
        //模糊查询保留值
        if (membercardPO != null) {
            request.setAttribute("blurMembercard", membercardPO);
        }
        if (page == null) {
            page = 0;
        }
        //page为初始页，pageSize表一页显示多少条
        PageHelper.startPage(page, ModuleConstant.PAGESIZE);
        List<MembercardPO> list = membercardDao.selectMembercard(membercardPO);

        PageInfo pageInfo = new PageInfo(list);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("membercardVOList", list);
        return "membercard/membercardList";
    }


    @Override
    public MembercardPO selectMembercardByID(Integer membercardID) {
        return membercardDao.selectMembercardByID(membercardID);
    }

    @Override
    public List<MembercardPO> findAllMembercardName() {
        return membercardDao.findAllMembercardName();
    }
}
