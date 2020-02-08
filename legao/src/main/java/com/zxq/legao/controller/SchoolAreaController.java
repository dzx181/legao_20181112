package com.zxq.legao.controller;

import com.zxq.legao.entity.po.SchoolAreaPO;
import com.zxq.legao.entity.vo.SchoolAreaVO;
import com.zxq.legao.service.SchoolAreaService;
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
import static com.zxq.legao.constant.ModuleEnum.SCHOOL_AREA;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dengzhenxiang
 * @since 2018-11-27
 */
@Controller

public class SchoolAreaController {
    @Autowired
    private SchoolAreaService schoolAreaService;

    /**
     * 文件导出
     * @return
     * @throws IOException
     */
    @RequestMapping("/schoolAreaPackExportExcel")
    public ResponseEntity<byte[]> importExcel() throws IOException {
        // 下载文件路径,文件对象,user所有字段值
        String path = UPLOADFILE_PATH+ "校区信息表" + ".xls";
        String[] thTitle = {"校区id", "校区名称","地址", "负责人","负责人电话"};
        // 获取所有的用户
        List<SchoolAreaVO> allSchoolArea = schoolAreaService.selectAllSchoolArea();
        // 调用导出Excel方法
        ExportUtil.exportFile(thTitle,"校区信息表",allSchoolArea,path,SCHOOL_AREA);
        // 文件下载
        return ExportUtil.download(path, "校区信息表"+ ".xls");
    }

    /**
     * 跳转到schoolAreaAdd
     */
    @RequestMapping("/schoolAreaAdd")
    public String jumpSchoolAreaAdd(HttpServletRequest request) {
        request.setAttribute("type", request.getAttribute("type"));
        return "schoolArea/schoolAreaAdd";
    }

    /**
     * 查询校区
     */
    @RequestMapping("/selectSchoolArea")
    public String selectSchoolArea(SchoolAreaPO schoolAreaPO, HttpServletRequest request, Integer page) {

        return schoolAreaService.selectSchoolArea(page, schoolAreaPO, request);
    }

    /**
     * 添加校区
     */
    @RequestMapping("/insertSchoolArea")
    public String insertSchoolArea(SchoolAreaPO schoolArea, HttpServletRequest request) {
        if (schoolAreaService.insertSchoolArea(schoolArea) > 0) {
            request.setAttribute("type", "yes");
        } else {
            request.setAttribute("type", "no");
        }

        ServletContext servletContext = request.getServletContext();
        List<SchoolAreaVO> allSchoolArea = schoolAreaService.findAllSchoolAreaName(new SchoolAreaPO());
        servletContext.setAttribute("allSchoolArea",allSchoolArea);
        return "schoolArea/schoolAreaAdd";
    }

    /**
     * 删除校区
     */
    @RequestMapping("/deleteSchoolAreas")
    public String deleteSchoolAreas(Integer[] caption, HttpServletRequest request) {
        if (caption.length!=0){
            for (int i = 0; i < caption.length; i++) {
                if (caption[i].equals(1)){
                    request.setAttribute("delete_msg", "不允许删除初始化值（校区）");
                     return "forward:/selectSchoolArea";
                }
            }
        }
        schoolAreaService.deleteSchoolArea(Arrays.asList(caption));
        ServletContext servletContext = request.getServletContext();
        List<SchoolAreaVO> allSchoolArea = schoolAreaService.findAllSchoolAreaName(new SchoolAreaPO());
        servletContext.setAttribute("allSchoolArea",allSchoolArea);
        return "forward:/selectSchoolArea";


    }

    /**
     * 根据校区id查找校区
     */
    @RequestMapping("/editSchoolArea")
    public String editSchoolArea(@RequestParam("schoolAreaId") Integer schoolAreaId, HttpServletRequest request) {
        SchoolAreaVO schoolAreaVO = schoolAreaService.selectSchoolAreaByID(schoolAreaId);
        request.setAttribute("schoolAreaByID", schoolAreaVO);
        return "forward:/editSchoolAreaFrom";

    }

    /**
     * 编辑校区
     */
    @RequestMapping("/editSchoolAreaFrom")
    public String editSchoolAreaFrom(HttpServletRequest request) {
        SchoolAreaVO schoolAreaVO = (SchoolAreaVO) request.getAttribute("schoolAreaByID");
        request.setAttribute("schoolAreaEdit", schoolAreaVO);
        return "schoolArea/schoolAreaEdit";

    }

    /**
     * 保存校区更改信息
     */
    @RequestMapping("/saveSchoolArea")
    public String saveSchoolArea(SchoolAreaPO schoolArea,HttpServletRequest request) {
        schoolAreaService.updateSchoolArea(schoolArea);
        ServletContext servletContext = request.getServletContext();
        List<SchoolAreaVO> allSchoolArea = schoolAreaService.findAllSchoolAreaName(new SchoolAreaPO());
        servletContext.setAttribute("allSchoolArea",allSchoolArea);
        return "redirect:/selectSchoolArea";
    }
}
