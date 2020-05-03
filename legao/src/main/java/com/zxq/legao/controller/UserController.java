package com.zxq.legao.controller;

import com.zxq.legao.entity.po.*;
import com.zxq.legao.entity.vo.*;
import com.zxq.legao.service.*;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.zxq.legao.constant.ModuleConstant.UPLOADFILE_PATH;
import static com.zxq.legao.constant.ModuleEnum.USER;

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
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SchoolAreaService schoolAreaService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private JobService jobService;

    @Autowired
    private ClassRoomService classRoomService;

    @Autowired
    private EmployService employService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private SeriesService seriesService;

    @Autowired
    private DateService dateService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ClasstimepackService classtimepackService;

    @Autowired
    private MembercardService membercardService;

    @Autowired
    private DepositService depositService;

    @RequestMapping("/")
    public String view() {
            return "login";
    }

    /**
     * 文件导出
     * @return
     * @throws IOException
     */
    @RequestMapping("/userExportExcel")
    public ResponseEntity<byte[]> importExcel() throws IOException {
        // 下载文件路径,文件对象,user所有字段值

        String path = UPLOADFILE_PATH  + "用户信息表" + ".xls";
        String[] thTitle = { "用户id" ,"用户名", "状态", "创建时间"};
        // 获取所有的用户
        List<UserVO> allUser = userService.selectAllUser();
        // 调用导出Excel方法
        ExportUtil.exportFile(thTitle,"用户信息表",allUser, path,USER);
        // 文件下载
        return ExportUtil.download(path, "用户信息表"+ ".xls");
    }
    /**
     * 登录
     */
    @RequestMapping("/toLogin")
    public String toLogin(UserPO userPO, HttpServletRequest request) {
        UserVO userVO1 = userService.selectUserByNameAndPass(userPO);
        if (userVO1 == null || userVO1.getUsername().equals("")) {
            request.setAttribute("msg", "登录名或者密码错误！");
            return "login";
        }else {
            //判断是否重复登录
            ServletContext servletContext = request.getServletContext();

            //放入session和application
            HttpSession session = request.getSession();
            session.setAttribute("user", userVO1);
            //初始化
            List<SchoolAreaVO> allSchoolArea = schoolAreaService.findAllSchoolAreaName(new SchoolAreaPO());
            List<EmployVO> allTeacherName = employService.selectAllTeacherName();
            List<StudentVO> allStudentName = studentService.selectAllStudentNameInContract();
            List<StudentVO> allStudentNames =  studentService.selectAllStudentName();
            List<JobVO> allJobName = jobService.selectAllJobName();
            List<ClassRoomPO> allClassRoomName = classRoomService.findAllClassRoomName();
            List<EmployPO> allemploy = employService.selectAllEmploy();
            List<CoursePO> allCourseName = courseService.findAllCourseName();
            List<SeriesVO> allSeriesName = seriesService.findAllSeriesName();
            List<DateVO> allDate = dateService.findAllDate();
            List<ClasstimepackPO> allClasstimepack = classtimepackService.findAllClasstimepackName();
            List<DepositPO> allDeposit = depositService.findAllDepositName();
            List<MembercardPO> allMembercard = membercardService.findAllMembercardName();

            servletContext.setAttribute("allStudentName", allStudentName);
            servletContext.setAttribute("allStudentNames", allStudentNames);
            servletContext.setAttribute("allSchoolArea", allSchoolArea);
            servletContext.setAttribute("allTeacherName", allTeacherName);
            servletContext.setAttribute("allJobName", allJobName);
            servletContext.setAttribute("allClassRoomName", allClassRoomName);
            servletContext.setAttribute("allemploy", allemploy);
            servletContext.setAttribute("allCourseName", allCourseName);
            servletContext.setAttribute("allSeriesName", allSeriesName);
            servletContext.setAttribute("allDate", allDate);
            servletContext.setAttribute("allClasstimepack", allClasstimepack);
            servletContext.setAttribute("allDeposit", allDeposit);
            servletContext.setAttribute("allMembercard", allMembercard);
            //session设置过期时间
            session.setMaxInactiveInterval(3600);
            return "main";
        }

    }

    /**
     * 注销退出
     */
    @RequestMapping("/logOff")
    public String logOff(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "login";
    }

    /**
     * 跳转各个页面
     */
    @RequestMapping("/left")
    public String jumpLeft() {
        return "left";
    }
    /**
     * 跳转各个页面
     */
    @RequestMapping("/main")
    public String jumpMain() {
        return "main";
    }
    /**
     * 跳转各个页面
     */
    @RequestMapping("/right")
    public String jumpRight() {
        return "right";
    }
    /**
     * 跳转各个页面
     */
    @RequestMapping("/top")
    public String jumpTop() {
        return "top";
    }

    /**
     * 跳转到userAdd
     */
    @RequestMapping("/userAdd")
    public String jumpUserAdd(HttpServletRequest request) {
        request.setAttribute("type", request.getAttribute("type"));
        return "user/userAdd";
    }

    /**
     * 查询用户
     */
    @RequestMapping("/selectUser")
    public String selectUser(UserPO userPO, HttpServletRequest request, Integer page) {

        return userService.selectUser(page, userPO, request);
    }

    /**
     * 添加用户
     */
    @RequestMapping("/insertUser")
    public String insertUser(UserPO user, HttpServletRequest request) {
        if (userService.insertUser(user) > 0) {
            request.setAttribute("type", "yes");
        } else {
            request.setAttribute("type", "no");
        }
        return "user/userAdd";
    }

    /**
     * 判断登录名是否重复
     */
    @RequestMapping("/isReLoginName")
    @ResponseBody
    public String isReLoginName(@RequestBody UserPO userPO) {
        List<UserVO> hasUser = userService.findUsername(userPO);
        if (hasUser.size() > 0) {
            // 设置为false代表登录名重复
            return "false";
        } else {
            return "true";
        }

    }

    /**
     * 删除用户
     */
    @RequestMapping("/deleteUsers")
    public String deleteUsers(Integer[] caption, HttpServletRequest request) {
        UserVO userVO = (UserVO) request.getSession().getAttribute("user");
        Integer userID = userVO.getId();
        boolean flag = userService.isDeleteItself(caption, userID);

        if (flag) {
            request.setAttribute("isDeleteItself", flag);
            return "forward:/selectUser";
        } else {
            userService.deleteUser(Arrays.asList(caption));
            return "redirect:/selectUser";
        }

    }

    /**
     * 根据id查找用户
     */
    @RequestMapping("/editUser")
    public String editUser(@RequestParam("userId") Integer userId, HttpServletRequest request) {
        UserVO userVO = userService.selectUserByID(userId);
        request.setAttribute("userByID", userVO);
        return "forward:/editUserFrom";
    }

    /**
     * 根据给编辑页赋值
     */
    @RequestMapping("/editUserFrom")
    public String editUserFrom(HttpServletRequest request) {
        UserVO userVO = (UserVO) request.getAttribute("userByID");
        request.setAttribute("userEdit", userVO);
        return "user/userEdit";
    }

    /**
     * 保存编辑值
     */
    @RequestMapping("/saveUser")
    public String saveUser(UserPO user) {
        userService.updateUser(user);
        return "redirect:/selectUser";
    }

}
