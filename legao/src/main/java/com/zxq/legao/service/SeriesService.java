package com.zxq.legao.service;

import com.zxq.legao.entity.po.SeriesPO;
import com.zxq.legao.entity.vo.SeriesVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SeriesService {
    int insertSeries(SeriesPO seriesPO);

    int deleteSeries(List<Integer> seriesIDs);

    int updateSeries(SeriesPO seriesPO);

    String selectSeries(Integer page, SeriesPO seriesO, HttpServletRequest request);

    SeriesVO selectSeriesByID(Integer seriesID);

    List<SeriesVO> findAllSeriesName();

    List<SeriesVO> selectAllSeries();



}
