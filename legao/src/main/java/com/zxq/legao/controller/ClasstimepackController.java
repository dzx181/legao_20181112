package com.zxq.legao.controller;


import com.zxq.legao.entity.po.ClasstimepackPO;
import com.zxq.legao.service.ClasstimepackService;
import com.zxq.legao.util.ExportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.zxq.legao.constant.ModuleConstant.UPLOADFILE_PATH;
import static com.zxq.legao.constant.ModuleEnum.CLASS_TIME_PACK;

/**
 * <p>
 * 课时包表 前端控制器
 * </p>
 *
 * @author dengzhenxiang
 * @since 2019-02-18
 */
@Controller
public class ClasstimepackController {
    @Autowired
    private ClasstimepackService classtimepackService;
    /**
     * 文件导出
     * @return
     * @throws IOException
     */
    @RequestMapping("/classTimePackExportExcel")
    public ResponseEntity<byte[]> importExcel() throws IOException {
        // 下载文件路径,文件对象,user所有字段值
        String path = UPLOADFILE_PATH+ "课时包信息表" + ".xls";
        String[] thTitle = {"课时包id", "课时包名称","课时", "课时价格", "优先级", "备注"};
        // 获取所有的用户
        List<ClasstimepackPO> allClassTimePack = classtimepackService.selectAllClassTimePack();
        // 调用导出Excel方法
        ExportUtil.exportFile(thTitle,"课时包信息表",allClassTimePack,path,CLASS_TIME_PACK);
        // 文件下载
        return ExportUtil.download(path, "课时包信息表"+ ".xls");
    }

    /**
     * 跳转到classtimepackAdd
     */
    @RequestMapping("/classtimepackAdd")
    public String jumpClasstimepackAdd(HttpServletRequest request) {
        request.setAttribute("type", request.getAttribute("type"));
        return "classtimepack/classtimepackAdd";
    }

    /**
     * 查询课时包
     */
    @RequestMapping("/selectClasstimepack")
    public String selectClasstimepack(ClasstimepackPO classtimepackPO, HttpServletRequest request, Integer page) {

        return classtimepackService.selectClasstimepack(page, classtimepackPO, request);
    }

    /**
     * 添加课时包
     */
    @RequestMapping("/insertClasstimepack")
    public String insertClasstimepack(ClasstimepackPO classtimepack, HttpServletRequest request) {
        if (classtimepackService.insertClasstimepack(classtimepack) > 0) {
            request.setAttribute("type", "yes");
        } else {
            request.setAttribute("type", "no");
        }
        ServletContext servletContext = request.getServletContext();
        List<ClasstimepackPO> allClasstimepack = classtimepackService.findAllClasstimepackName();
        servletContext.setAttribute("allClasstimepack",allClasstimepack);
        return "classtimepack/classtimepackAdd";
    }

    /**
     * 删除课时包
     */
    @RequestMapping("/deleteClasstimepacks")
    public String deleteClasstimepacks(Integer[] caption, HttpServletRequest request) {

        classtimepackService.deleteClasstimepack(Arrays.asList(caption));
        ServletContext servletContext = request.getServletContext();
        List<ClasstimepackPO> allClasstimepack = classtimepackService.findAllClasstimepackName();
        servletContext.setAttribute("allClasstimepack",allClasstimepack);
        return "forward:/selectClasstimepack";


    }

    /**
     * 根据id查找课时包
     */
    @RequestMapping("/editClasstimepack")
    public String editClasstimepack(@RequestParam("classtimepackId") Integer classtimepackId, HttpServletRequest request) {
        ClasstimepackPO classtimepackPO = classtimepackService.selectClasstimepackByID(classtimepackId);
        request.setAttribute("classtimepackByID", classtimepackPO);
        return "forward:/editClasstimepackFrom";

    }

    /**
     * 根据给编辑页赋值
     */
    @RequestMapping("/editClasstimepackFrom")
    public String editClasstimepackFrom(HttpServletRequest request) {
        ClasstimepackPO classtimepackPO = (ClasstimepackPO) request.getAttribute("classtimepackByID");
        request.setAttribute("classtimepackEdit", classtimepackPO);
        return "classtimepack/classtimepackEdit";

    }

    /**
     * 保存编辑值
     */
    @RequestMapping("/saveClasstimepack")
    public String saveClasstimepack(ClasstimepackPO classtimepack,HttpServletRequest request) {
        classtimepackService.updateClasstimepack(classtimepack);
        ServletContext servletContext = request.getServletContext();
        List<ClasstimepackPO> allClasstimepack = classtimepackService.findAllClasstimepackName();
        servletContext.setAttribute("allClasstimepack",allClasstimepack);
        return "redirect:/selectClasstimepack";

    }
}

