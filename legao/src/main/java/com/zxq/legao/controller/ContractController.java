package com.zxq.legao.controller;


import com.alibaba.fastjson.JSON;
import com.zxq.legao.entity.po.ClasstimepackPO;
import com.zxq.legao.entity.po.ContractPO;
import com.zxq.legao.entity.vo.ContractVO;
import com.zxq.legao.entity.vo.StudentVO;
import com.zxq.legao.service.ClasstimepackService;
import com.zxq.legao.service.ContractService;
import com.zxq.legao.service.StudentService;
import com.zxq.legao.util.ExportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static com.zxq.legao.constant.ModuleConstant.UPLOADFILE_PATH;
import static com.zxq.legao.constant.ModuleEnum.CONTRACT;

/**
 * <p>
 * 合同表 前端控制器
 * </p>
 *
 * @author dengzhenxiang
 * @since 2019-02-18
 */
@Controller
public class ContractController {
    @Autowired
    private ContractService contractService;
    @Autowired
    private ClasstimepackService classtimepackService;
    @Autowired
    private StudentService studentService;

    /**
     * 文件导出
     * @return
     * @throws IOException
     */
    @RequestMapping("/contractExportExcel")
    public ResponseEntity<byte[]> importExcel() throws IOException {
        // 下载文件路径,文件对象,user所有字段值
        String path = UPLOADFILE_PATH+ "合同信息表" + ".xls";
        String[] thTitle = {"合同id","合同编号", "学生", "签约日期", "课时包名称", "价格", "折扣", "订金", "实际支付", "支付方式", "赠送课时", "开始时间", "结束时间", "业绩所属", "会员卡编号", "备注","课时(h)","剩余课时(h)","总课时(h)","校区"};
        // 获取所有的用户
        List<ContractVO> allContract = contractService.selectAllContract();
        // 调用导出Excel方法
        ExportUtil.exportFile(thTitle,"合同信息表",allContract, path,CONTRACT);
        // 文件下载
        return ExportUtil.download(path, "合同信息表"+ ".xls");
    }
    /**
     * 跳转到contractAdd
     */
    @RequestMapping("/contractAdd")
    public String jumpContractAdd(HttpServletRequest request) {
        request.setAttribute("type", request.getAttribute("type"));
        return "contract/contractAdd";
    }

    /**
     * 查询合同
     */
    @RequestMapping("/selectContract")
    public String selectContract(ContractPO contractPO, HttpServletRequest request, Integer page) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(contractPO.getSignDate()!=null){
            contractPO.setSignDateStr(simpleDateFormat.format(contractPO.getSignDate()));
        }
        if(contractPO.getStartTime()!=null){
            contractPO.setStartTimeStr(simpleDateFormat.format(contractPO.getStartTime()));
        }
        if(contractPO.getEndTime()!=null){
            contractPO.setEndTimeStr(simpleDateFormat.format(contractPO.getEndTime()));
        }
        return contractService.selectContract(page, contractPO, request);
    }

    /**
     * 添加合同
     */
    @RequestMapping("/insertContract")
    public String insertContract(ContractPO contract, HttpServletRequest request) {
        if (contractService.insertContract(contract,request).equals("")) {
            request.setAttribute("type", "yes");
        } else {
            request.setAttribute("type", "no");
        }
        ServletContext servletContext = request.getServletContext();
        List<StudentVO> allStudentName = studentService.selectAllStudentNameInContract();
        servletContext.setAttribute("allStudentName", allStudentName);
        return "contract/contractAdd";
    }


    /**
     * 删除合同
     */
    @RequestMapping("/deleteContracts")
    public String deleteContracts(Integer[] caption,HttpServletRequest request) {
        contractService.deleteContract(Arrays.asList(caption));
        ServletContext servletContext = request.getServletContext();
        List<StudentVO> allStudentName = studentService.selectAllStudentNameInContract();
        servletContext.setAttribute("allStudentName", allStudentName);
        return "redirect:/selectContract";

    }

    /**
     * 根据id查找合同
     */
    @RequestMapping("/editContract")
    public String editContract(@RequestParam("contractId") Integer contractId, HttpServletRequest request) {
        ContractPO contractPO = contractService.selectContractByID(contractId);
        request.setAttribute("contractByID", contractPO);
        return "forward:/editContractFrom";

    }

    /**
     * 根据给编辑页赋值
     */
    @RequestMapping("/editContractFrom")
    public String editContractFrom(HttpServletRequest request) {
        ContractPO contractPO = (ContractPO) request.getAttribute("contractByID");
        request.setAttribute("contractEdit", contractPO);
        return "contract/contractEdit";

    }

    /**
     * 保存编辑值
     */
    @RequestMapping("/saveContract")
    public String saveContract(ContractPO contract,HttpServletRequest request) {
        contractService.updateContract(contract);
        ServletContext servletContext = request.getServletContext();
        List<StudentVO> allStudentName = studentService.selectAllStudentNameInContract();
        servletContext.setAttribute("allStudentName", allStudentName);
        return "redirect:/selectContract";

    }

    @RequestMapping("/selectPackByPackId")
    @ResponseBody
    public String selectPackByPackId(@RequestBody ClasstimepackPO classtimepackPO) {
        String courseStr = null;
        ClasstimepackPO classtimepackPO1 = classtimepackService.selectClasstimepackByID(classtimepackPO.getId());
        if (classtimepackPO1 !=null) {

            courseStr = JSON.toJSONString(classtimepackPO1);
            return courseStr;
        } else {
            courseStr = JSON.toJSONString("");
            return courseStr;
        }

    }

}

