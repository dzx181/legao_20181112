package com.zxq.legao.dao;

import com.zxq.legao.entity.po.ContractPO;
import com.zxq.legao.entity.vo.ContractVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 合同表 Mapper 接口
 * </p>
 *
 * @author dengzhenxiang
 * @since 2019-02-18
 */
public interface ContractDao  {
    int insertContract(ContractPO contractPO);

    int deleteContract(List<Integer> contractIDs);

    int updateContract(ContractPO contractPO);

    List<ContractVO> selectContract(@Param("contractPO") ContractPO contractPO, @Param("fields") List<String> fields);

    ContractPO selectContractByID(Integer contractPO);

    ContractPO selectContractByStudentId(Integer studentId);

    int updateRemainClassTime(ContractPO contractPO);

    List<Integer> selectStudentInContract();

    List<ContractVO> selectAllContract();
}
