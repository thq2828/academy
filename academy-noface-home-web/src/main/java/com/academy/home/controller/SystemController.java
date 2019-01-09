package com.academy.home.controller;

import com.academy.core.pojo.Response;
import com.academy.core.pojo.User;
import com.academy.core.service.UserService;
import com.academy.home.utils.HttpUtil;
import com.academy.home.utils.JJWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.academy.home.utils.HttpUtil.getUsr;

@RestController
@Slf4j
public class SystemController {

    @Resource
    private UserService userService;

    /**
     * 登錄
     */
    @PostMapping("/a/login")
    public Response login(@RequestParam("code") String code, HttpServletResponse response) {
        log.info("登錄 code = {}", code);
        Map userInfo;
        try{
            userInfo = HttpUtil.getUsr(code);
        }catch (Exception e) {
            e.printStackTrace();
            return new Response<>(-1, "登錄異常", code);
        }
        if(userInfo == null) {
            log.info("获取用户信息失败 code = {}", code);
            return new Response<>(-1, "登錄異常", code);
        }
        String openId = userInfo.get("openid").toString();
        User check = userService.findByOpenId(openId);

        // 如果openid没有对应用户，新增一个用户
        if(check == null) {
            User user = new User();
            user.setOpenId(openId);
            user.setNick(userInfo.get("nickname").toString());
            user.setPic(userInfo.get("headimgurl").toString());
            user.setAddress(userInfo.get("province").toString());
            user.setSignDays("[]");
            Long id = userService.insertUser(user);
            check = userService.findByOpenId(openId);
            log.info("新增用戶 openid = {}, id = {}", openId, id);
        }
        // 如果有对应openid或者新增用户之后
        log.info("登录用户 openid = {}， id = {}", openId, check.getId());
        String uid = JJWTUtil.sign(check.getId());
        //设置响应头
        response.setHeader("Authorization", uid);
        return new Response<>(0, "success", check);
    }
}
