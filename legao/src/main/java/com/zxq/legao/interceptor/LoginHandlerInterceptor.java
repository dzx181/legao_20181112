package com.zxq.legao.interceptor;

import com.zxq.legao.entity.vo.UserVO;
import com.zxq.legao.exception.LoginException;
import com.zxq.legao.exception.SessionTimeOutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Description:
 * <p>
 * 登录拦截
 * </p>
 *
 * @author dengzhenxiang
 * @Date 2019/2/16 17:14
 */
@Slf4j
public class LoginHandlerInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        int timeout = session.getMaxInactiveInterval();
        UserVO user = (UserVO) session.getAttribute("user");
        if (timeout <= 0) {
            throw new SessionTimeOutException("用户登录超时");
        } else if (user == null) {
          throw  new LoginException("用户没有登录");
        } else {
            return true;
        }


    }


}

