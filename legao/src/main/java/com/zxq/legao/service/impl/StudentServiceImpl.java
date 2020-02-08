package com.zxq.legao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxq.legao.dao.ContractDao;
import com.zxq.legao.dao.StudentDao;
import com.zxq.legao.dao.UserDao;
import com.zxq.legao.entity.po.StudentPO;
import com.zxq.legao.entity.vo.StudentVO;
import com.zxq.legao.entity.vo.UserVO;
import com.zxq.legao.service.StudentService;
import com.zxq.legao.constant.ModuleConstant;
import com.zxq.legao.util.ConverstUtil;
import com.zxq.legao.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.zxq.legao.constant.ModuleConstant.STUDENT_FIELDS_DB;
import static com.zxq.legao.constant.ModuleConstant.STUDENT_FIELDS_ZH;

/**
 * Description:
 * <p>
 * 用户前端控制器
 * </p>
 *
 * @author dengzhenxiang
 * @Date 2018/11/11 17:41
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ContractDao contractDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertStudent(StudentPO studentPO) {
        String age = DateUtil.getAge(studentPO.getBirthday());
        studentPO.setStuAge(age);

        return studentDao.insertStudent(studentPO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteStudent(List<Integer> studentIDs) {
        return studentDao.deleteStudent(studentIDs);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateStudent(StudentPO studentPO) {
        return studentDao.updateStudent(studentPO);
    }

    @Override
    public String selectStudent(Integer page, StudentPO studentPO, HttpServletRequest request) {
        //模糊查询保留值
        if (studentPO != null) {
            request.setAttribute("blurStudent", studentPO);
        }
        if (page == null) {
            page = 0;
        }

        //查询当前登录用户选择展示的字段
        HttpSession session = request.getSession();
        UserVO userVO = (UserVO) session.getAttribute("user");
        UserVO userVO1 = userDao.selectFieldsByUserID(userVO.getId());
        String fields = "";
        if (userVO1 == null) {
            fields = null;
        } else {
            fields = userDao.selectFieldsByUserID(userVO.getId()).getSelectStudentFields();
        }
        List<StudentVO> list = null;
        List<String> defaultStudentFieldsDB = Arrays.asList(ModuleConstant.DEFAULT_STUDENT_FIELDS_DB);
        List<String> defaultStudentFieldsZH = Arrays.asList(ModuleConstant.DEFAULT_STUDENT_FIELDS_ZH);
        List<String> studentFieldsDB = ConverstUtil.stringToList(fields, STUDENT_FIELDS_DB);
        List<String> studentFieldsZH = ConverstUtil.stringToList(fields, STUDENT_FIELDS_ZH);

        //page为初始页，pageSize表一页显示多少条
        PageHelper.startPage(page, ModuleConstant.PAGESIZE);
        if (fields == null) {
            list = studentDao.selectStudent(studentPO, defaultStudentFieldsDB);
            request.setAttribute("FieldZH", defaultStudentFieldsZH);
        } else {
            list = studentDao.selectStudent(studentPO, studentFieldsDB);
            request.setAttribute("FieldZH", studentFieldsZH);
        }

        //给页面赋值
        PageInfo pageInfo = new PageInfo(list);
        request.setAttribute("studentVOList", list);
        request.setAttribute("pageInfo", pageInfo);
        return "student/studentList";
    }


    @Override
    public StudentVO selectStudentByID(Integer studentID) {
        return studentDao.selectStudentByID(studentID);
    }


    @Override
    public List<StudentVO> selectAllStudentName() {
        return studentDao.selectAllStudentName();
    }

    @Override
    public List<StudentVO> selectAllStudentNameInContract() {
        List<Integer> list = contractDao.selectStudentInContract();
        List<StudentVO> studentVOS = null;
        if (!list.isEmpty()){
            studentVOS = new ArrayList<>(list.size());
            for (Integer i:list) {
                StudentVO studentVO = studentDao.selectStudentByID(i);
                studentVOS.add(studentVO);
            }
        }
        return studentVOS;
    }

    @Override
    public List<StudentVO> selectAllStudent() {
        return studentDao.selectStudent(new StudentPO(),Arrays.asList(STUDENT_FIELDS_DB));
    }
}
