package com.zxq.legao.interceptor;

import com.zxq.legao.exception.LoginException;
import com.zxq.legao.exception.SessionTimeOutException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class LoginExceptionControllerAdvice {
    /**
     * 内部错误
     */
    @ExceptionHandler(LoginException.class)
    public String loginHandleException(Exception exception, HttpServletRequest request) {
        request.setAttribute("msg", "请先登录！");
        return "/login";
    }
    @ExceptionHandler(SessionTimeOutException.class)
    public String sessionHandleException(Exception exception, HttpServletRequest request) {
        request.setAttribute("msg", "登录超时，请重新登录！");
        return "/login";
    }
}
