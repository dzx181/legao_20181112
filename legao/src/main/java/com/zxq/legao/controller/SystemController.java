package com.zxq.legao.controller;


import com.zxq.legao.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dengzhenxiang
 * @since 2018-11-27
 */
@Controller
public class SystemController {
    @Autowired
    private SystemService systemService;


    /**
     * 跳转到字段设置页
     */
    @RequestMapping("/fieldSet")
    public String jumpfieldSet() {
        return "system/fieldSet";
    }

    /**
     * 更新用户设置的学员模块字段
     */
    @PostMapping("/selectStudentField")
    @ResponseBody
    public String selectStudentField(@RequestBody List<String> caption, HttpServletRequest request) {
        return systemService.updateFieldsSet("selectStudentFields", caption, request);
    }

    /**
     * 更新用户设置的员工模块字段
     */
    @PostMapping("/selectEmployField")
    @ResponseBody
    public String selectEmployFieldForm(@RequestBody List<String> caption, HttpServletRequest request) {
        return systemService.updateFieldsSet("selectEmployFields", caption, request);

    }

    /**
     * 更新用户设置的合同模块字段
     */
    @PostMapping("/selectContractField")
    @ResponseBody
    public String selectContractField(@RequestBody List<String> caption, HttpServletRequest request) {
        return systemService.updateFieldsSet("selectContractFields", caption, request);
    }
}
