package com.zxq.legao.service;

import com.zxq.legao.entity.po.ContractPO;
import com.zxq.legao.entity.vo.ContractVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 合同表 服务类
 * </p>
 *
 * @author dengzhenxiang
 * @since 2019-02-18
 */
public interface ContractService {
    String insertContract(ContractPO contractPO,HttpServletRequest request);

    int deleteContract(List<Integer> contractIDs);

    int updateContract(ContractPO contractPO);

    String selectContract(Integer page, ContractPO contractPO, HttpServletRequest request);

    ContractPO selectContractByID(Integer contractID);

    ContractPO selectContractByStudentId(Integer studentId);

    List<Integer> selectStudentInContract();

    List<ContractVO> selectAllContract();


}
