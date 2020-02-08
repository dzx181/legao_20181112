package com.zxq.legao.service.impl;

import com.alibaba.fastjson.JSON;
import com.zxq.legao.dao.SystemDao;
import com.zxq.legao.dao.UserDao;
import com.zxq.legao.entity.vo.UserVO;
import com.zxq.legao.service.SystemService;
import com.zxq.legao.util.ConverstUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
/**
 * Description:
 * <p>
 *     用户前端控制器
 * </p>
 * @author dengzhenxiang
 * @Date 2018/11/11 17:41
 */
@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    private SystemDao systemDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String updateFieldsSet(String field, List<String> caption, HttpServletRequest request) {

        HttpSession session = request.getSession();
        UserVO userVO = (UserVO) session.getAttribute("user");
        if (caption.size() == 0) {
            return JSON.toJSONString("请选择要展示的字段");
        } else if (caption.size() < 5) {
            return JSON.toJSONString("至少展示5个字段");

        } else if (caption.size() > 8) {
            return JSON.toJSONString("至多展示8个字段");

        } else if (userVO == null) {
            return JSON.toJSONString("请先登录");

        } else {
            String fieldStr = ConverstUtil.strToDBStr(caption);
            systemDao.updateStuFieldsSet(field, fieldStr, userVO.getId());
            return JSON.toJSONString("设置成功");

        }
    }
}
