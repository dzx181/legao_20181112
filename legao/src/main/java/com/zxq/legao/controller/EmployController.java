package com.zxq.legao.controller;


import com.zxq.legao.entity.po.EmployPO;
import com.zxq.legao.entity.vo.EmployVO;
import com.zxq.legao.service.EmployService;
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
import static com.zxq.legao.constant.ModuleEnum.EMPLOY;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dengzhenxiang
 * @since 2018-11-27
 */
@Controller
public class EmployController {
    @Autowired
    private EmployService employService;

    /**
     * 文件导出
     * @return
     * @throws IOException
     */
    @RequestMapping("/employExportExcel")
    public ResponseEntity<byte[]> importExcel() throws IOException {
        // 下载文件路径,文件对象,user所有字段值
        String path = UPLOADFILE_PATH+ "员工信息表" + ".xls";
        String[] thTitle =  {"员工id","姓名", "性别", "联系电话", "生日", "入职日期", "职位", "基本工资", "全勤", "餐补", "课时费", "总课时（h）", "提成", "校区", "状态"};
        // 获取所有的用户
        List<EmployVO> allEmploy = employService.selectAllEmploys();
        // 调用导出Excel方法
        ExportUtil.exportFile(thTitle,"员工信息表",allEmploy, path,EMPLOY);
        // 文件下载
        return ExportUtil.download(path, "员工信息表"+ ".xls");
    }
    /**
     * 跳转到employAdd
     */
    @RequestMapping("/employAdd")
    public String jumpEmployAdd(HttpServletRequest request) {
        request.setAttribute("type", request.getAttribute("type"));
        return "employ/employAdd";
    }

    /**
     * 查询员工
     */
    @RequestMapping("/selectEmploy")
    public String selectEmploy(EmployPO employPO, HttpServletRequest request, Integer page) {
        return employService.selectEmploy(page, employPO, request);
    }

    /**
     * 添加员工
     */
    @RequestMapping("/insertEmploy")
    public String insertEmploy(EmployPO employ, HttpServletRequest request) {
        if (employService.insertEmploy(employ) > 0) {

            request.setAttribute("type", "yes");
        } else {
            request.setAttribute("type", "no");
        }
        ServletContext servletContext = request.getServletContext();
        List<EmployPO> allemploy = employService.selectAllEmploy();
        List<EmployVO> allTeacherName = employService.selectAllTeacherName();
        servletContext.setAttribute("allTeacherName", allTeacherName);
        servletContext.setAttribute("allemploy", allemploy);

        return "employ/employAdd";
    }


    /**
     * 删除员工
     */
    @RequestMapping("/deleteEmploys")
    public String deleteEmploys(Integer[] caption, HttpServletRequest request) {

        employService.deleteEmploy(Arrays.asList(caption));
        ServletContext servletContext = request.getServletContext();
        List<EmployPO> allemploy = employService.selectAllEmploy();
        List<EmployVO> allTeacherName = employService.selectAllTeacherName();
        servletContext.setAttribute("allTeacherName", allTeacherName);
        servletContext.setAttribute("allemploy", allemploy);
        return "redirect:/selectEmploy";


    }

    /**
     * 根据id查找员工
     */
    @RequestMapping("/editEmploy")
    public String editEmploy(@RequestParam("employId") Integer employId, HttpServletRequest request) {
        EmployPO employPO = employService.selectEmployByID(employId);
        request.setAttribute("employByID", employPO);
        return "forward:/editEmployFrom";

    }

    /**
     * 根据给编辑页赋值
     */
    @RequestMapping("/editEmployFrom")
    public String editEmployFrom(HttpServletRequest request) {
        EmployPO employPO = (EmployPO) request.getAttribute("employByID");
        request.setAttribute("employEdit", employPO);
        return "employ/employEdit";

    }

    /**
     * 保存编辑值
     */
    @RequestMapping("/saveEmploy")
    public String saveEmploy(EmployPO employ, HttpServletRequest request) {
        employService.updateEmploy(employ);
        ServletContext servletContext = request.getServletContext();
        List<EmployPO> allemploy = employService.selectAllEmploy();
        List<EmployVO> allTeacherName = employService.selectAllTeacherName();
        servletContext.setAttribute("allTeacherName", allTeacherName);
        servletContext.setAttribute("allemploy", allemploy);
        return "redirect:/selectEmploy";

    }


}

