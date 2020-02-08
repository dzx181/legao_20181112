package com.zxq.legao.service;


import com.zxq.legao.entity.po.JobPO;
import com.zxq.legao.entity.vo.JobVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author dengzhenxiang
 * @since 2018-11-27
 */
public interface JobService {
    int addJob(JobPO jobPO);

    int deleteJob(List<Integer> jobIDs);

    int updateJob(JobPO jobPO);

    String selectJob(Integer page, JobPO jobPO, HttpServletRequest request);

    JobVO selectJobByID(Integer jobID);

    List<JobVO> findJobName(JobPO jobPO);

    List<JobVO> selectAllJobName();

    List<JobVO> selectAllJob();
}
