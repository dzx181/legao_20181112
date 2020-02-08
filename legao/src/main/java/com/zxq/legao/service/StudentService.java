package com.zxq.legao.service;

import com.zxq.legao.entity.po.StudentPO;
import com.zxq.legao.entity.vo.StudentVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface StudentService {
    int insertStudent(StudentPO studentPO);

    int deleteStudent(List<Integer> studentIDs);

    int updateStudent(StudentPO studentPO);

    String selectStudent(Integer page, StudentPO studentPO, HttpServletRequest request);

    StudentVO selectStudentByID(Integer studentID);

    List<StudentVO> selectAllStudentName();

    List<StudentVO> selectAllStudentNameInContract();

    List<StudentVO> selectAllStudent();
}
