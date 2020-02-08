package com.zxq.legao.service;

import com.zxq.legao.entity.po.DatePO;
import com.zxq.legao.entity.vo.DateVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface DateService {
    int insertDate(DatePO datePO);

    int deleteDate(List<Integer> dateIDs);

    int updateDate(DatePO datePO);

    String selectDate(Integer page, DatePO datePO, HttpServletRequest request);

    DateVO selectDateByID(Integer dateID);

    List<DateVO> findAllDate();

    List<DateVO> selectAllDate();
}
