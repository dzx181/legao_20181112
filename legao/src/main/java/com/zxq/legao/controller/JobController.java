package com.zxq.legao.controller;


import com.zxq.legao.entity.po.JobPO;
import com.zxq.legao.entity.vo.JobVO;
import com.zxq.legao.service.JobService;
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
import java.util.Arrays;
import java.util.List;

import static com.zxq.legao.constant.ModuleConstant.UPLOADFILE_PATH;
import static com.zxq.legao.constant.ModuleEnum.JOB;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dengzhenxiang
 * @since 2018-11-27
 */
@Controller
public class JobController {
    @Autowired
    private JobService jobService;

    /**
     * 文件导出
     * @return
     * @throws IOException
     */
    @RequestMapping("/jobPackExportExcel")
    public ResponseEntity<byte[]> importExcel() throws IOException {
        // 下载文件路径,文件对象,user所有字段值
        String path = UPLOADFILE_PATH+ "职位信息表" + ".xls";
        String[] thTitle = {"职位id", "职位名称", "备注","创建时间"};
        // 获取所有的用户
        List<JobVO> allJob = jobService.selectAllJob();
        // 调用导出Excel方法
        ExportUtil.exportFile(thTitle,"职位信息表",allJob,path,JOB);
        // 文件下载
        return ExportUtil.download(path, "职位信息表"+ ".xls");
    }
    /**
     * 跳转到jobAd
     */
    @RequestMapping("/jobAdd")
    public String jumpJobAdd(HttpServletRequest request) {
        request.setAttribute("type", request.getAttribute("type"));
        return "job/jobAdd";
    }

    /**
     * 查询用户
     */
    @RequestMapping("/selectJob")
    public String selectJob(JobPO jobPO, HttpServletRequest request, Integer page) {

        return jobService.selectJob(page, jobPO, request);
    }

    /**
     * 添加用户
     */
    @RequestMapping("/insertJob")
    public String insertJob(JobPO job, HttpServletRequest request) {
        if (jobService.addJob(job) > 0) {
            request.setAttribute("type", "yes");
        } else {
            request.setAttribute("type", "no");
        }
        ServletContext servletContext = request.getServletContext();
        List<JobVO> allJobName = jobService.selectAllJobName();
        servletContext.setAttribute("allJobName", allJobName);
        return "job/jobAdd";
    }


    /**
     * 删除用户
     */
    @RequestMapping("/deleteJobs")
    public String deleteJobs(Integer[] caption, HttpServletRequest request) {
        int flag = jobService.deleteJob(Arrays.asList(caption));
        if (flag == -1) {
            request.setAttribute("delete_msg", "不允许删除初始化值（老师）");
        }
        ServletContext servletContext = request.getServletContext();
        List<JobVO> allJobName = jobService.selectAllJobName();
        servletContext.setAttribute("allJobName", allJobName);
        return "forward:/selectJob";


    }

    /**
     * 根据id查找用
     */
    @RequestMapping("/editJob")
    public String editJob(@RequestParam("jobId") Integer jobId, HttpServletRequest request) {
        JobVO jobVO = jobService.selectJobByID(jobId);
        request.setAttribute("jobByID", jobVO);
        return "forward:/editJobFrom";

    }

    /**
     * 根据给编辑页赋
     */
    @RequestMapping("/editJobFrom")
    public String editJobFrom(HttpServletRequest request) {
        JobVO jobVO = (JobVO) request.getAttribute("jobByID");
        request.setAttribute("jobEdit", jobVO);
        return "job/jobEdit";
    }

    /**
     * 保存编辑
     */
    @RequestMapping("/saveJob")
    public String saveJob(JobPO job, HttpServletRequest request) {
        jobService.updateJob(job);
        ServletContext servletContext = request.getServletContext();
        List<JobVO> allJobName = jobService.selectAllJobName();
        servletContext.setAttribute("allJobName", allJobName);
        return "redirect:/selectJob";
    }

    /**
     * 判断登录名是否重复
     */
    @RequestMapping("/isReJobName")
    @ResponseBody
    public String isReLoginName(@RequestBody JobPO jobPO) {
        List<JobVO> hasJob = jobService.findJobName(jobPO);
        if (hasJob.size() > 0) {
            // 设置为false代表登录名重复
            return "false";
        } else {
            return "true";
        }

    }
}

