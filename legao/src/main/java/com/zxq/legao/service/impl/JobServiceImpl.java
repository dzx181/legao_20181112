package com.zxq.legao.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxq.legao.dao.JobDao;
import com.zxq.legao.entity.po.JobPO;
import com.zxq.legao.entity.vo.JobVO;
import com.zxq.legao.constant.ModuleConstant;

import com.zxq.legao.service.JobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dengzhenxiang
 * @since 2018-11-27
 */
@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobDao jobDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addJob(JobPO jobPO) {
        return jobDao.addJob(jobPO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteJob(List<Integer> jobIDs) {
        for (int i = 0; i < jobIDs.size(); i++) {
            if (jobIDs.get(i) == 1) {
                return -1;
            }
        }
        return jobDao.deleteJob(jobIDs);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateJob(JobPO jobPO) {
        return jobDao.updateJob(jobPO);
    }

    @Override
    public String selectJob(Integer page, JobPO jobPO, HttpServletRequest request) {
        //模糊查询保留值
        if (jobPO != null) {
            if (jobPO.getName() != null) {
                request.setAttribute("blurJob", jobPO);
            }
        }
        if (page == null) {
            page = 0;
        }
        //page为初始页，pageSize表一页显示多少条
        PageHelper.startPage(page, ModuleConstant.PAGESIZE);
        List<JobVO> list = jobDao.selectJob(jobPO);
        PageInfo pageInfo = new PageInfo(list);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("jobVOList", list);
        return "job/jobList";

    }

    @Override
    public JobVO selectJobByID(Integer jobID) {
        return jobDao.selectJobByID(jobID);
    }

    @Override
    public List<JobVO> findJobName(JobPO jobPO) {
        return jobDao.findJobName(jobPO);
    }

    @Override
    public List<JobVO> selectAllJobName() {
        return jobDao.selectAllJobName();
    }

    @Override
    public List<JobVO> selectAllJob() {
        return jobDao.selectAllJob();
    }
}
