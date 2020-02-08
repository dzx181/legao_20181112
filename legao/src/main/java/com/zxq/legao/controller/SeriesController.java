package com.zxq.legao.controller;

import com.zxq.legao.entity.po.SeriesPO;
import com.zxq.legao.entity.vo.SeriesVO;
import com.zxq.legao.service.SeriesService;
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
import static com.zxq.legao.constant.ModuleEnum.SERIES;

/**
 * Description:
 * <p>
 * 用户前端控制器
 * </p>
 *
 * @author dengzhenxiang
 * @Date 2018/11/11 17:41
 */
@Controller
public class SeriesController {
    @Autowired
    private SeriesService seriesService;
    /**
     * 文件导出
     * @return
     * @throws IOException
     */
    @RequestMapping("/seriesExportExcel")
    public ResponseEntity<byte[]> importExcel() throws IOException {
        // 下载文件路径,文件对象,user所有字段值
        String path = UPLOADFILE_PATH+ "课程系列信息表" + ".xls";
        String[] thTitle = {"课程系列id","课程系列名称", "教具", "课时", "年龄段"};
        // 获取所有的用户
        List<SeriesVO> allSeries = seriesService.selectAllSeries();
        // 调用导出Excel方法
        ExportUtil.exportFile(thTitle,"课程系列信息表",allSeries, path,SERIES);
        // 文件下载
        return ExportUtil.download(path, "课程系列信息表"+ ".xls");
    }

    /**
     * 跳转到seriesAdd
     */
    @RequestMapping("/seriesAdd")
    public String jumpSeriesAdd(HttpServletRequest request) {
        request.setAttribute("type", request.getAttribute("type"));
        return "series/seriesAdd";
    }

    /**
     * 查询课程
     */
    @RequestMapping("/selectSeries")
    public String selectSeries(SeriesPO seriesPO, HttpServletRequest request, Integer page) {

        return seriesService.selectSeries(page, seriesPO, request);
    }

    /**
     * 添加课程
     */
    @RequestMapping("/insertSeries")
    public String insertSeries(SeriesPO series, HttpServletRequest request) {
        if (seriesService.insertSeries(series) > 0) {
            request.setAttribute("type", "yes");
        } else {
            request.setAttribute("type", "no");
        }
        ServletContext servletContext = request.getServletContext();
        List<SeriesVO> allSeriesName = seriesService.findAllSeriesName();
        servletContext.setAttribute("allSeriesName", allSeriesName);
        return "series/seriesAdd";
    }

    /**
     * 删除课程
     */
    @RequestMapping("/deleteSeriess")
    public String deleteSeriess(Integer[] caption, HttpServletRequest request) {
        seriesService.deleteSeries(Arrays.asList(caption));
        ServletContext servletContext = request.getServletContext();
        List<SeriesVO> allSeriesName = seriesService.findAllSeriesName();
        servletContext.setAttribute("allSeriesName", allSeriesName);
        return "forward:/selectSeries";
    }

    /**
     * 根据id查找课程
     */
    @RequestMapping("/editSeries")
    public String editSeries(@RequestParam("seriesId") Integer seriesId, HttpServletRequest request) {
        SeriesVO seriesVO = seriesService.selectSeriesByID(seriesId);
        request.setAttribute("seriesByID", seriesVO);
        return "forward:/editSeriesFrom";
    }

    /**
     * 根据给编辑页赋值
     */
    @RequestMapping("/editSeriesFrom")
    public String editSeriesFrom(HttpServletRequest request) {
        SeriesVO seriesVO = (SeriesVO) request.getAttribute("seriesByID");
        request.setAttribute("seriesEdit", seriesVO);
        return "series/seriesEdit";

    }

    /**
     * 保存编辑值
     */
    @RequestMapping("/saveSeries")
    public String saveSeries(SeriesPO series, HttpServletRequest request) {
        seriesService.updateSeries(series);
        ServletContext servletContext = request.getServletContext();
        List<SeriesVO> allSeriesName = seriesService.findAllSeriesName();
        servletContext.setAttribute("allSeriesName", allSeriesName);
        return "redirect:/selectSeries";

    }
}
