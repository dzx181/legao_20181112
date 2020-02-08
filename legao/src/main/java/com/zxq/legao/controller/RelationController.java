package com.zxq.legao.controller;

import com.zxq.legao.entity.po.RelationPO;
import com.zxq.legao.entity.vo.RelationVO;
import com.zxq.legao.entity.vo.UserVO;
import com.zxq.legao.service.RelationService;
import com.zxq.legao.util.ExportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.zxq.legao.constant.ModuleConstant.UPLOADFILE_PATH;
import static com.zxq.legao.constant.ModuleEnum.SIGN;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dengzhenxiang
 * @since 2018-11-27
 */
@Controller
public class RelationController {
    @Autowired
    private RelationService relationService;

    /**
     * 文件导出
     * @return
     * @throws IOException
     */
    @RequestMapping("/relationExportExcel")
    public ResponseEntity<byte[]> importExcel() throws IOException {
        // 下载文件路径,文件对象,user所有字段值
        String path = UPLOADFILE_PATH+ "签到信息表" + ".xls";
        String[] thTitle = {"签到id","课程名称", "老师","学员","电话","学员生日", "性别", "周数","时间段","签到状态"};
        // 获取所有的用户
        List<RelationVO> allRelation = relationService.selectAllRelation();
        // 调用导出Excel方法
        ExportUtil.exportFile(thTitle,"签到信息表",allRelation, path,SIGN);
        // 文件下载
        return ExportUtil.download(path, "签到信息表"+ ".xls");
    }
    /**
     * 跳转到relationAdd
      */
    @RequestMapping("/relationAdd")
    public String jumpRelationAdd(HttpServletRequest request) {
        request.setAttribute("type", request.getAttribute("type"));
        return "relation/relationAdd";
    }

    /**
     * 查询签到
     */
    @RequestMapping("/selectRelation")
    public String selectRelation(RelationPO relationPO, HttpServletRequest request, Integer page) {

        return relationService.selectRelation(page, relationPO, request);
    }



    /**
     * 删除签到
      */
    @RequestMapping("/deleteRelations")
    public String deleteRelations(Integer[] caption, HttpServletRequest request) {

        relationService.deleteRelation(Arrays.asList(caption));
        return "forward:/selectRelation";


    }

    /**
     * 根据scheduleID查找签到记录
      */
    @RequestMapping("/editRelation")
    public String editRelation(@RequestParam("scheduleId") Integer scheduleId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserVO userVO = (UserVO) session.getAttribute("user");
        if (userVO!=null) {
            if (Integer.valueOf(userVO.getStatus()) != 1) {
                request.setAttribute("msg","抱歉，您没有修改权限");
                return "forward:/selectSchedule";
            }
        }
        List<RelationVO> relationVOList = relationService.selectRelationByScheduleID(scheduleId);
        if (relationVOList.size()!=0) {
            request.setAttribute("relationEdit", relationVOList.get(0));
            request.setAttribute("relationVOList", relationVOList);
        }
        return "relation/relationEdit";

    }

    /**
     * 批量修改签到状态
     */
    @RequestMapping("/updateBatchRelation")
    public String updateBatchRelation(RelationPO relationPO,HttpServletRequest request) {
        UserVO userVO = (UserVO) request.getSession().getAttribute("user");
        relationPO.setModifyPerson(userVO.getUsername());
        relationService.updateBatchRelation(relationPO);
        List<RelationVO> relationVOList = relationService.selectRelationByScheduleID(relationPO.getScheduleID());
        request.setAttribute("relationEdit",relationVOList.get(0));
        request.setAttribute("relationVOList", relationVOList);
        return "relation/relationEdit";

    }


}
