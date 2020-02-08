package com.zxq.legao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxq.legao.dao.SeriesDao;
import com.zxq.legao.dao.StudentDao;
import com.zxq.legao.entity.po.SeriesPO;
import com.zxq.legao.entity.vo.SeriesVO;
import com.zxq.legao.service.SeriesService;
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
public class SeriesServiceImpl implements SeriesService {
    @Autowired
    private SeriesDao seriesDao;

    @Autowired
    private StudentDao studentDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSeries(SeriesPO seriesPO) {
        return seriesDao.insertSeries(seriesPO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteSeries(List<Integer> seriesIDs) {
        return seriesDao.deleteSeries(seriesIDs);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateSeries(SeriesPO seriesPO) {
        return seriesDao.updateSeries(seriesPO);
    }

    @Override
    public String selectSeries(Integer page, SeriesPO seriesPO, HttpServletRequest request) {
        //模糊查询保留值
        if (seriesPO != null) {
            request.setAttribute("blurSeries", seriesPO);
        }
        if (page == null) {
            page = 0;
        }
        //page为初始页，pageSize表一页显示多少条
        PageHelper.startPage(page, ModuleConstant.PAGESIZE);
        List<SeriesVO> list = seriesDao.selectSeries(seriesPO);
        PageInfo pageInfo = new PageInfo(list);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("seriesVOList", list);
        return "series/seriesList";
    }


    @Override
    public SeriesVO selectSeriesByID(Integer seriesID) {
        return seriesDao.selectSeriesByID(seriesID);
    }

    @Override
    public List<SeriesVO> findAllSeriesName() {
        return seriesDao.findAllSeriesName();
    }

    @Override
    public List<SeriesVO> selectAllSeries() {
        return seriesDao.selectSeries(null);
    }
}
