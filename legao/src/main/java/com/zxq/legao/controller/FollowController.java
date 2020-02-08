package com.zxq.legao.controller;

import com.zxq.legao.entity.po.FollowPO;
import com.zxq.legao.service.FollowService;
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

import static com.zxq.legao.constant.ModuleConstant.UPLOADFILE_PATH;
import static com.zxq.legao.constant.ModuleEnum.FOLLOW;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dengzhenxiang
 * @since 2018-11-27
 */
@Controller
public class FollowController {
    @Autowired
    private FollowService followService;

    /**
     * 文件导出
     * @return
     * @throws IOException
     */
    @RequestMapping("/followExportExcel")
    public ResponseEntity<byte[]> importExcel() throws IOException {
        // 下载文件路径,文件对象,user所有字段值
        String path = UPLOADFILE_PATH+ "跟进信息表" + ".xls";
        String[] thTitle = {"跟进id", "学生", "顾问", "跟进方式","创建日期"};
        // 获取所有的用户
        List<FollowPO> allFollow = followService.selectAllFollow();
        // 调用导出Excel方法
        ExportUtil.exportFile(thTitle,"跟进信息表",allFollow, path,FOLLOW);
        // 文件下载
        return ExportUtil.download(path, "跟进信息表"+ ".xls");
    }
    /**
     * 跳转到followAdd
      */
    @RequestMapping("/followAdd")
    public String jumpFollowAdd(HttpServletRequest request) {
        request.setAttribute("type", request.getAttribute("type"));
        return "follow/followAdd";
    }

    /**
     * 查询跟进人
     */
    @RequestMapping("/selectFollow")
    public String selectFollow(FollowPO followPO, HttpServletRequest request, Integer page) {

        return followService.selectFollow(page, followPO, request);
    }

    /**
     * 添加跟进人
      */
    @RequestMapping("/insertFollow")
    public String insertFollow(FollowPO follow, HttpServletRequest request) {
        if (followService.insertFollow(follow) > 0) {
            request.setAttribute("type", "yes");
        } else {
            request.setAttribute("type", "no");
        }
        ServletContext servletContext = request.getServletContext();
        List<FollowPO> allFollow = followService.findAllFollowName();
        servletContext.setAttribute("allFollow",allFollow);
        return "follow/followAdd";
    }

    /**
     * 删除跟进人
      */
    @RequestMapping("/deleteFollows")
    public String deleteFollows(Integer[] caption, HttpServletRequest request) {

        followService.deleteFollow(Arrays.asList(caption));
        ServletContext servletContext = request.getServletContext();
        List<FollowPO> allFollow = followService.findAllFollowName();
        servletContext.setAttribute("allFollow",allFollow);
        return "forward:/selectFollow";


    }

    /**
     * 根据id查找跟进人
      */
    @RequestMapping("/editFollow")
    public String editFollow(@RequestParam("followId") Integer followId, HttpServletRequest request) {
        FollowPO followPO = followService.selectFollowByID(followId);
        request.setAttribute("followByID", followPO);
        return "forward:/editFollowFrom";

    }

    /**
     * 根据给编辑页赋值
      */
    @RequestMapping("/editFollowFrom")
    public String editFollowFrom(HttpServletRequest request) {
        FollowPO followPO = (FollowPO) request.getAttribute("followByID");
        request.setAttribute("followEdit", followPO);
        return "follow/followEdit";

    }

    /**
     * 保存编辑值
      */
    @RequestMapping("/saveFollow")
    public String saveFollow(FollowPO follow,HttpServletRequest request) {
        followService.updateFollow(follow);
        ServletContext servletContext = request.getServletContext();
        List<FollowPO> allFollow = followService.findAllFollowName();
        servletContext.setAttribute("allFollow",allFollow);
        return "redirect:/selectFollow";

    }
}
