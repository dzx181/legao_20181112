package com.zxq.legao.dao;

import com.zxq.legao.entity.po.DatePO;

import com.zxq.legao.entity.vo.DateVO;

import java.util.List;

public interface DateDao {
    int insertDate(DatePO datePO);

    int deleteDate(List<Integer> dateIDs);

    int updateDate(DatePO datePO);

    List<DateVO> selectDate(DatePO datePO);

    DateVO selectDateByID(Integer dateID);

    List<DateVO> selectAllDate();
}
