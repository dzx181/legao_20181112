package com.zxq.legao.dao;


import com.zxq.legao.entity.po.JobPO;

import com.zxq.legao.entity.vo.JobVO;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author dengzhenxiang
 * @since 2018-11-27
 */
public interface JobDao {
    int addJob(JobPO jobPO);

    int deleteJob(List<Integer> jobIDs);

    int updateJob(JobPO jobPO);

    List<JobVO> selectJob(JobPO jobPO);

    JobVO selectJobByID(Integer jobID);

    List<JobVO> findJobName(JobPO jobPO);

    List<JobVO> selectAllJobName();

    List<JobVO> selectAllJob();

}
