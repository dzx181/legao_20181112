package com.zxq.legao.controller;


import com.zxq.legao.entity.po.ClassRoomPO;
import com.zxq.legao.entity.vo.ClassRoomVO;
import com.zxq.legao.service.ClassRoomService;
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
import static com.zxq.legao.constant.ModuleEnum.CLASSROOM;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dengzhenxiang
 * @since 2018-11-27
 */
@Controller
public class ClassRoomController {
    @Autowired
    private ClassRoomService classRoomService;
    /**
     * 文件导出
     * @return
     * @throws IOException
     */
    @RequestMapping("/classRoomExportExcel")
    public ResponseEntity<byte[]> importExcel() throws IOException {
        // 下载文件路径,文件对象,user所有字段值
        String path = UPLOADFILE_PATH + "教室信息表" + ".xls";
        String[] thTitle = {"教室id","教室名称", "年龄段", "负责人"};
        // 获取所有的用户
        List<ClassRoomPO> allClassRoom = classRoomService.selectAllClassRoom();
        // 调用导出Excel方法
        ExportUtil.exportFile(thTitle,"教室信息表",allClassRoom, path,CLASSROOM);
        // 文件下载
        return ExportUtil.download(path, "教室信息表"+ ".xls");
    }
    /**
     * 跳转到classRoomAdd
     */
    @RequestMapping("/classRoomAdd")
    public String jumpClassRoomAdd(HttpServletRequest request) {
        request.setAttribute("type", request.getAttribute("type"));
        return "classRoom/classRoomAdd";
    }

    /**
     * 查找教室
     */
    @RequestMapping("/selectClassRoom")
    public String selectClassRoom(ClassRoomPO classRoomPO, HttpServletRequest request, Integer page) {

        return classRoomService.selectClassRoom(page, classRoomPO, request);
    }

    /**
     * 添加教室
     */
    @RequestMapping("/insertClassRoom")
    public String insertClassRoom(ClassRoomPO classRoom, HttpServletRequest request) {
        if (classRoomService.insertClassRoom(classRoom) > 0) {
            request.setAttribute("type", "yes");
        } else {
            request.setAttribute("type", "no");
        }
        ServletContext servletContext = request.getServletContext();
        List<ClassRoomPO> allClassRoomName = classRoomService.findAllClassRoomName();
        servletContext.setAttribute("allClassRoomName", allClassRoomName);

        return "classRoom/classRoomAdd";
    }

    /**
     * 删除教室
     */
    @RequestMapping("/deleteClassRooms")
    public String deleteClassRooms(Integer[] caption, HttpServletRequest request) {
        classRoomService.deleteClassRoom(Arrays.asList(caption));
        ServletContext servletContext = request.getServletContext();
        List<ClassRoomPO> allClassRoomName = classRoomService.findAllClassRoomName();
        servletContext.setAttribute("allClassRoomName", allClassRoomName);
        return "forward:/selectClassRoom";


    }

    /**
     * 根据教室id查找教室
     */
    @RequestMapping("/editClassRoom")
    public String editClassRoom(@RequestParam("classRoomId") Integer classRoomId, HttpServletRequest request) {
        ClassRoomVO classRoomVO = classRoomService.selectClassRoomByID(classRoomId);
        request.setAttribute("classRoomByID", classRoomVO);
        return "forward:/editClassRoomFrom";

    }

    /**
     * 给编辑页赋值
     */
    @RequestMapping("/editClassRoomFrom")
    public String editClassRoomFrom(HttpServletRequest request) {
        ClassRoomVO classRoomVO = (ClassRoomVO) request.getAttribute("classRoomByID");
        request.setAttribute("classRoomEdit", classRoomVO);
        return "classRoom/classRoomEdit";

    }

    /**
     * 保存编辑页信息
     */
    @RequestMapping("/saveClassRoom")
    public String saveClassRoom(ClassRoomPO classRoom, HttpServletRequest request) {
        classRoomService.updateClassRoom(classRoom);
        ServletContext servletContext = request.getServletContext();
        List<ClassRoomPO> allClassRoomName = classRoomService.findAllClassRoomName();
        servletContext.setAttribute("allClassRoomName", allClassRoomName);
        return "redirect:/selectClassRoom";

    }
}

