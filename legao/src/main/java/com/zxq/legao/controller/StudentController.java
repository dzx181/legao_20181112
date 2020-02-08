package com.zxq.legao.controller;

import com.zxq.legao.entity.po.StudentPO;
import com.zxq.legao.entity.vo.StudentVO;
import com.zxq.legao.service.StudentService;
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

import static com.zxq.legao.constant.ModuleConstant.STUDENT_FIELDS_ZH;
import static com.zxq.legao.constant.ModuleConstant.UPLOADFILE_PATH;
import static com.zxq.legao.constant.ModuleEnum.STUDENT;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dengzhenxiang
 * @since 2018-11-27
 */
@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 文件导出
     * @return
     * @throws IOException
     */
    @RequestMapping("/studentExportExcel")
    public ResponseEntity<byte[]> importExcel() throws IOException {
        // 下载文件路径,文件对象,user所有字段值
        String path = UPLOADFILE_PATH+ "学员信息表" + ".xls";
        // 获取所有的用户
        List<StudentVO> allStudent = studentService.selectAllStudent();
        // 调用导出Excel方法
        String[] targetArray = new String[STUDENT_FIELDS_ZH.length+1];
        targetArray[0] = "学员id";
        System.arraycopy(STUDENT_FIELDS_ZH,0,targetArray,1,STUDENT_FIELDS_ZH.length);
        ExportUtil.exportFile(targetArray,"学员信息表",allStudent, path,STUDENT);
        // 文件下载
        return ExportUtil.download(path, "学员信息表"+ ".xls");
    }
    /**
     *     跳转到studentAdd
      */
    @RequestMapping("/studentAdd")
    public String jumpStudentAdd(HttpServletRequest request) {
        request.setAttribute("type", request.getAttribute("type"));
        return "student/studentAdd";
    }

    /**
     * 查询学员
     */
    @RequestMapping("/selectStudent")
    public String selectStudent(StudentPO studentPO, HttpServletRequest request, Integer page) {
        return studentService.selectStudent(page, studentPO, request);
    }

    /**
     * 添加学员
      */
    @RequestMapping("/insertStudent")
    public String insertStudent(StudentPO student, HttpServletRequest request) {
        if (studentService.insertStudent(student) > 0) {
            request.setAttribute("type", "yes");
        } else {
            request.setAttribute("type", "no");
        }
        ServletContext servletContext = request.getServletContext();

        List<StudentVO> allStudentNames = studentService.selectAllStudentName();
        servletContext.setAttribute("allStudentNames", allStudentNames);


        return "student/studentAdd";
    }


    /**
     * 删除学员
      */
    @RequestMapping("/deleteStudents")
    public String deleteStudents(Integer[] caption,HttpServletRequest request) {
        studentService.deleteStudent(Arrays.asList(caption));
        ServletContext servletContext = request.getServletContext();

        List<StudentVO> allStudentNames = studentService.selectAllStudentName();
        servletContext.setAttribute("allStudentNames", allStudentNames);
        return "redirect:/selectStudent";


    }

    /**
     * 根据id查找学员
      */
    @RequestMapping("/editStudent")
    public String editStudent(@RequestParam("studentId") Integer studentId, HttpServletRequest request) {
        StudentVO studentVO = studentService.selectStudentByID(studentId);
        request.setAttribute("studentByID", studentVO);
        return "forward:/editStudentFrom";

    }

    /**
     * 根据给编辑页赋值
      */
    @RequestMapping("/editStudentFrom")
    public String editStudentFrom(HttpServletRequest request) {
        StudentVO studentVO = (StudentVO) request.getAttribute("studentByID");
        request.setAttribute("studentEdit", studentVO);
        return "student/studentEdit";

    }

    /**
     * 保存编辑值
      */
    @RequestMapping("/saveStudent")
    public String saveStudent(StudentPO student,HttpServletRequest request) {
        studentService.updateStudent(student);
        ServletContext servletContext = request.getServletContext();

        List<StudentVO> allStudentNames = studentService.selectAllStudentName();
        servletContext.setAttribute("allStudentNames", allStudentNames);
        return "redirect:/selectStudent";

    }
}
