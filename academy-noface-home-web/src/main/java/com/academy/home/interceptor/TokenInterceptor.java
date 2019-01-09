package com.academy.home.interceptor;

import com.academy.core.pojo.User;
import com.academy.core.service.UserService;
import com.academy.home.utils.LoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        log.info("拦截器");
        Long uid = LoginUtil.getUid(request);
        log.info("uid = {}", uid);
        if(uid == 0L) {
            response.sendRedirect("/nologin");
            return false;
        }
        log.info("登录 uid = {}", uid);
        // 判断用户
        User user = userService.findById(uid);
        if(user != null) {
            log.info("login success id = {}", uid);
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

