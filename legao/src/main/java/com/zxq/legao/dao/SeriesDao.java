package com.zxq.legao.dao;

import com.zxq.legao.entity.po.SeriesPO;
import com.zxq.legao.entity.vo.SeriesVO;

import java.util.List;

public interface SeriesDao {
    int insertSeries(SeriesPO seriesPO);

    int deleteSeries(List<Integer> seriesIDs);

    int updateSeries(SeriesPO seriesPO);

    List<SeriesVO> selectSeries(SeriesPO seriesPO);

    SeriesVO selectSeriesByID(Integer seriesID);

    List<SeriesVO> findAllSeriesName();
}
