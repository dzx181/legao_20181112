package com.zxq.legao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxq.legao.dao.FollowDao;
import com.zxq.legao.entity.po.FollowPO;
import com.zxq.legao.service.FollowService;
import com.zxq.legao.constant.ModuleConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * Description:
 * <p>
 *     用户前端控制器
 * </p>
 * @author dengzhenxiang
 * @Date 2018/11/11 17:41
 */
@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    private FollowDao followDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertFollow(FollowPO followPO) {
        return followDao.insertFollow(followPO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteFollow(List<Integer> followIDs) {
        return followDao.deleteFollow(followIDs);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateFollow(FollowPO followPO) {
        return followDao.updateFollow(followPO);
    }

    @Override
    public String selectFollow(Integer page, FollowPO followPO, HttpServletRequest request) {
        //模糊查询保留值
        if (followPO != null) {
            request.setAttribute("blurFollow", followPO);
        }
        if (page == null) {
            page = 0;
        }
        //page为初始页，pageSize表一页显示多少条
        PageHelper.startPage(page, ModuleConstant.PAGESIZE);
        List<FollowPO> list = followDao.selectFollow(followPO);

        PageInfo pageInfo = new PageInfo(list);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("followVOList", list);
        return "follow/followList";
    }


    @Override
    public FollowPO selectFollowByID(Integer followID) {
        return followDao.selectFollowByID(followID);
    }

    @Override
    public List<FollowPO> findAllFollowName() {
        return followDao.findAllFollowName();
    }

    @Override
    public List<FollowPO> selectAllFollow() {
        return followDao.selectFollow(null);
    }
}
