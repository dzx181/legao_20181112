package com.zxq.legao.dao;

import com.zxq.legao.entity.po.StudentPO;

import com.zxq.legao.entity.vo.StudentVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {
    int insertStudent(StudentPO studentPO);

    int deleteStudent(List<Integer> studentIDs);

    int updateStudent(StudentPO studentPO);

    List<StudentVO> selectStudent(@Param("studentPO") StudentPO studentPO, @Param("fields") List<String> fields);

    StudentVO selectStudentByID(Integer studentPO);

    List<StudentVO> selectAllStudentName();

    StudentPO selectStudentNameByID(Integer studentID);


}
