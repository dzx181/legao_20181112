package com.zxq.legao.controller;

import com.zxq.legao.entity.po.DatePO;
import com.zxq.legao.entity.vo.DateVO;
import com.zxq.legao.service.DateService;
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
import static com.zxq.legao.constant.ModuleEnum.DATE;

/**
 * Description:
 * <p>
 * 日期前端控制器
 * </p>
 *
 * @author dengzhenxiang
 * @Date 2018/11/11 17:41
 */
@Controller
public class DateController {
    @Autowired
    private DateService dateService;
    /**
     * 文件导出
     * @return
     * @throws IOException
     */
    @RequestMapping("/dateExportExcel")
    public ResponseEntity<byte[]> importExcel() throws IOException {
        // 下载文件路径,文件对象,user所有字段值
        String path = UPLOADFILE_PATH + "时间段信息表" + ".xls";
        String[] thTitle = {"时间段id","时间段", "状态", "优先级", "备注"};
        // 获取所有的用户
        List<DateVO> allDate = dateService.selectAllDate();
        // 调用导出Excel方法
        ExportUtil.exportFile(thTitle,"时间段信息表",allDate, path,DATE);
        // 文件下载
        return ExportUtil.download(path, "时间段信息表"+ ".xls");
    }
    /**
     * 跳转到dateAdd
     */
    @RequestMapping("/dateAdd")
    public String jumpDateAdd(HttpServletRequest request) {
        request.setAttribute("type", request.getAttribute("type"));
        return "date/dateAdd";
    }

    /**
     * 查询时间段
     */
    @RequestMapping("/selectDate")
    public String selectDate(DatePO datePO, HttpServletRequest request, Integer page) {

        return dateService.selectDate(page, datePO, request);
    }

    /**
     * 添加时间段
     */
    @RequestMapping("/insertDate")
    public String insertDate(DatePO date, HttpServletRequest request) {
        if (dateService.insertDate(date) > 0) {
            request.setAttribute("type", "yes");
        } else {
            request.setAttribute("type", "no");
        }
        ServletContext servletContext = request.getServletContext();
        List<DateVO> allDate = dateService.findAllDate();
        servletContext.setAttribute("allDate", allDate);

        return "date/dateAdd";
    }

    /**
     * 删除时间段
     */
    @RequestMapping("/deleteDates")
    public String deleteDates(Integer[] caption, HttpServletRequest request) {

        dateService.deleteDate(Arrays.asList(caption));
        ServletContext servletContext = request.getServletContext();
        List<DateVO> allDate = dateService.findAllDate();
        servletContext.setAttribute("allDate", allDate);
        return "forward:/selectDate";


    }

    /**
     * 根据id查找时间段
     */
    @RequestMapping("/editDate")
    public String editDate(@RequestParam("dateId") Integer dateId, HttpServletRequest request) {
        DateVO dateVO = dateService.selectDateByID(dateId);
        request.setAttribute("dateByID", dateVO);
        return "forward:/editDateFrom";

    }

    /**
     * 根据给编辑页赋值
     */
    @RequestMapping("/editDateFrom")
    public String editDateFrom(HttpServletRequest request) {
        DateVO dateVO = (DateVO) request.getAttribute("dateByID");
        request.setAttribute("dateEdit", dateVO);
        return "date/dateEdit";

    }

    /**
     * 保存编辑值
     */
    @RequestMapping("/saveDate")
    public String saveDate(DatePO date, HttpServletRequest request) {
        dateService.updateDate(date);
        ServletContext servletContext = request.getServletContext();
        List<DateVO> allDate = dateService.findAllDate();
        servletContext.setAttribute("allDate", allDate);
        return "redirect:/selectDate";

    }
}
