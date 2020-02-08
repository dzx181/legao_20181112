package com.zxq.legao.controller;


import com.zxq.legao.entity.po.MembercardPO;
import com.zxq.legao.service.MembercardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 会员卡表 前端控制器
 * </p>
 *
 * @author dengzhenxiang
 * @since 2019-02-18
 */
@Controller
public class MembercardController {
    @Autowired
    private MembercardService membercardService;

    /**
     * 跳转到membercardAdd
     */
    @RequestMapping("/membercardAdd")
    public String jumpMembercardAdd(HttpServletRequest request) {
        request.setAttribute("type", request.getAttribute("type"));
        return "membercard/membercardAdd";
    }

    /**
     * 查询会员卡
     */
    @RequestMapping("/selectMembercard")
    public String selectMembercard(MembercardPO membercardPO, HttpServletRequest request, Integer page) {

        return membercardService.selectMembercard(page, membercardPO, request);
    }

    /**
     * 添加会员卡
     */
    @RequestMapping("/insertMembercard")
    public String insertMembercard(MembercardPO membercard, HttpServletRequest request) {
        if (membercardService.insertMembercard(membercard) > 0) {
            request.setAttribute("type", "yes");
        } else {
            request.setAttribute("type", "no");
        }
        ServletContext servletContext = request.getServletContext();
        List<MembercardPO> allMembercard = membercardService.findAllMembercardName();
        servletContext.setAttribute("allMembercard",allMembercard);
        return "membercard/membercardAdd";
    }

    /**
     * 删除会员卡
     */
    @RequestMapping("/deleteMembercards")
    public String deleteMembercards(Integer[] caption, HttpServletRequest request) {

        membercardService.deleteMembercard(Arrays.asList(caption));
        ServletContext servletContext = request.getServletContext();
        List<MembercardPO> allMembercard = membercardService.findAllMembercardName();
        servletContext.setAttribute("allMembercard",allMembercard);
        return "forward:/selectMembercard";


    }

    /**
     * 根据id查找会员卡
     */
    @RequestMapping("/editMembercard")
    public String editMembercard(@RequestParam("membercardId") Integer membercardId, HttpServletRequest request) {
        MembercardPO membercardPO = membercardService.selectMembercardByID(membercardId);
        request.setAttribute("membercardByID", membercardPO);
        return "forward:/editMembercardFrom";

    }

    /**
     * 根据给编辑页赋值
     */
    @RequestMapping("/editMembercardFrom")
    public String editMembercardFrom(HttpServletRequest request) {
        MembercardPO membercardPO = (MembercardPO) request.getAttribute("membercardByID");
        request.setAttribute("membercardEdit", membercardPO);
        return "membercard/membercardEdit";

    }

    /**
     * 保存编辑值
     */
    @RequestMapping("/saveMembercard")
    public String saveMembercard(MembercardPO membercard,HttpServletRequest request) {
        membercardService.updateMembercard(membercard);
        ServletContext servletContext = request.getServletContext();
        List<MembercardPO> allMembercard = membercardService.findAllMembercardName();
        servletContext.setAttribute("allMembercard",allMembercard);
        return "redirect:/selectMembercard";

    }
}

