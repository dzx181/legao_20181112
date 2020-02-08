package com.zxq.legao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxq.legao.dao.DateDao;
import com.zxq.legao.entity.po.DatePO;
import com.zxq.legao.entity.vo.DateVO;
import com.zxq.legao.service.DateService;
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
public class DateServiceImpl implements DateService {
    @Autowired
    private DateDao dateDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertDate(DatePO datePO) {
        String part1 = datePO.getTimeSection1();
        String part2 = datePO.getTimeSection();
        String timeSection = part1 +" - "+ part2;
        datePO.setTimeSection(timeSection);
        return dateDao.insertDate(datePO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteDate(List<Integer> dateIDs) {
        return dateDao.deleteDate(dateIDs);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateDate(DatePO datePO) {
        return dateDao.updateDate(datePO);
    }

    @Override
    public String selectDate(Integer page, DatePO datePO, HttpServletRequest request) {
        //模糊查询保留值
        if (datePO != null) {
            request.setAttribute("blurDate", datePO);
        }
        if (page == null) {
            page = 0;
        }
        //page为初始页，pageSize表一页显示多少条
        PageHelper.startPage(page, ModuleConstant.PAGESIZE);
        List<DateVO> list = dateDao.selectDate(datePO);

        PageInfo pageInfo = new PageInfo(list);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("dateVOList", list);
        return "date/dateList";
    }


    @Override
    public DateVO selectDateByID(Integer dateID) {
        return dateDao.selectDateByID(dateID);
    }

    @Override
    public List<DateVO> findAllDate() {
        return dateDao.selectAllDate();
    }

    @Override
    public List<DateVO> selectAllDate() {
        return dateDao.selectDate(null);
    }
}
