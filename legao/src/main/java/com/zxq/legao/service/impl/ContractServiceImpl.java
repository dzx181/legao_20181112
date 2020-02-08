package com.zxq.legao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxq.legao.dao.ClasstimepackDao;
import com.zxq.legao.dao.ContractDao;
import com.zxq.legao.dao.MembercardDao;
import com.zxq.legao.dao.UserDao;
import com.zxq.legao.entity.po.ClasstimepackPO;
import com.zxq.legao.entity.po.ContractPO;
import com.zxq.legao.entity.vo.ContractVO;
import com.zxq.legao.entity.vo.UserVO;
import com.zxq.legao.service.ContractService;
import com.zxq.legao.constant.ModuleConstant;
import com.zxq.legao.util.ConverstUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 合同表 服务实现类
 * </p>
 *
 * @author dengzhenxiang
 * @since 2019-02-18
 */
@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractDao contractDao;

    @Autowired
    private UserDao userDao;
    @Autowired
    private MembercardDao membercardDao;
    @Autowired
    private ClasstimepackDao classtimepackDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String insertContract(ContractPO contractPO,HttpServletRequest request) {
        ContractPO contractPO1 = contractDao.selectContractByStudentId(Integer.valueOf(contractPO.getStudentId()));
        if (contractPO1==null){
            //计算总课时
            ClasstimepackPO classtimepackPO = classtimepackDao.selectClasstimepackByID(contractPO.getClassPackageId());
            String remainClassTime = Float.valueOf(contractPO.getPresentationClassTime())  + Float.valueOf(classtimepackPO.getClassTime()) +"";
            contractPO.setRemainClassTime(remainClassTime);
            contractPO.setTotalClassTime(remainClassTime);
            contractDao.insertContract(contractPO);
            return "";
        }else {
            request.setAttribute("msg","该学员已有一份合同");
            return "contract/contractAdd";
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteContract(List<Integer> contractIDs) {
        return contractDao.deleteContract(contractIDs);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateContract(ContractPO contractPO) {

        return contractDao.updateContract(contractPO);
    }

    @Override
    public String selectContract(Integer page, ContractPO contractPO, HttpServletRequest request) {
        //模糊查询保留值
        if (contractPO != null) {
            request.setAttribute("blurContract", contractPO);
        }
        if (page == null) {
            page = 0;
        }

        //查询当前登录用户选择展示的字段
        HttpSession session = request.getSession();
        UserVO userVO = (UserVO) session.getAttribute("user");
        UserVO userVO1 = userDao.selectContractFieldsByUserID(userVO.getId());
        String fields = "";
        if (userVO1 == null){
            fields = null;
        }else {
            fields = userDao.selectContractFieldsByUserID(userVO.getId()).getSelectContractFields();
        }

        List<ContractVO> list = null;
        List<String> defaultContractFieldsDB = Arrays.asList(ModuleConstant.DEFAULT_CONTRACT_FIELDS_DB);
        List<String> defaultContractFieldsZH = Arrays.asList(ModuleConstant.DEFAULT_CONTRACT_FIELDS_ZH);
        List<String> contractFieldsDB = ConverstUtil.stringToList(fields, ModuleConstant.CONTRACT_FIELDS_DB);
        List<String> contractFieldsZH = ConverstUtil.stringToList(fields, ModuleConstant.CONTRACT_FIELDS_ZH);

        //page为初始页，pageSize表一页显示多少条
        PageHelper.startPage(page, ModuleConstant.PAGESIZE);
        if (fields == null) {
            list = contractDao.selectContract(contractPO, defaultContractFieldsDB);
            request.setAttribute("FieldZH", defaultContractFieldsZH);
        } else {
            list = contractDao.selectContract(contractPO, contractFieldsDB);
            request.setAttribute("FieldZH", contractFieldsZH);
        }

        if (contractPO.getStudentId() !=null){
            list.stream().filter(e -> e.getStudentVO().getName().equals(contractPO.getStudentId())).collect(Collectors.toList());
        }
        //给页面赋值
        PageInfo pageInfo = new PageInfo(list);
        request.setAttribute("contractVOList", list);
        request.setAttribute("pageInfo", pageInfo);
        return "contract/contractList";
    }


    @Override
    public ContractPO selectContractByID(Integer contractID) {
        return contractDao.selectContractByID(contractID);
    }

    @Override
    public ContractPO selectContractByStudentId(Integer studentId) {
        return contractDao.selectContractByStudentId(studentId);
    }

    @Override
    public List<Integer> selectStudentInContract() {
        return contractDao.selectStudentInContract();
    }

    @Override
    public List<ContractVO> selectAllContract() {
        return contractDao.selectAllContract();
    }

}
