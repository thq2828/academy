package com.academy.home.controller;

import com.academy.core.pojo.Code;
import com.academy.core.pojo.Response;
import com.academy.core.pojo.User;
import com.academy.core.service.UserService;
import com.academy.home.utils.LoginUtil;
import com.academy.home.utils.MsgUtil;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.print.attribute.standard.NumberUp;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 获取签到详情
     */
    @GetMapping("/a/u/student/sign")
    public Response getSign(HttpServletRequest request) {
        Long uid = LoginUtil.getUid(request);
        log.info("获取用户签到信息 uid = {}", uid);
        User user = userService.findById(uid);
        return new Response<>(0, "success", user);
    }

    /**
     * 点击签到
     */
    @PutMapping("/a/u/student/sign")
    public Response sign(HttpServletRequest request) {
        Long uid = LoginUtil.getUid(request);
        log.info("点击签到 uid = {}", uid);
        User user = userService.findById(uid);

        Integer day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        Integer lastSign = user.getLastSign();
        Integer nowSign = user.getNowSign();
        Integer mostSign = user.getMostSign();
        Integer bean = user.getBean();
        List<Integer> signList = user.getSignList();

        // 判断是否是合法签到
        if(day <= lastSign) {
            log.info("非法签到 day = {}", day);
            return new Response<>(-1, "非法签到", day);
        }
        signList.add(day);

        // 判断是否是连续签到，清空当前连续签到天数
        if(day - lastSign != 1) {
            log.info("非连续签到");
            nowSign = 0;
        }
        nowSign = nowSign + 1;
        log.info("当前连续签到 nowSign = {}", nowSign);

        // 最大连续签到天数
        if(nowSign > mostSign) {
            mostSign = nowSign;
            log.info("最大连续签到 mostSign = {}", mostSign);
        }

        // 签到获取逆袭豆
        if(nowSign > User.SIGN_UP) {
            bean = bean + User.SIGN_UP;
            log.info("连续签到5天以上 bean = {}", bean);
        }else {
            bean = bean + nowSign;
            log.info("正常签到 bean = {}", bean);
        }

        user.setNowSign(nowSign);
        user.setSignDays(signList.toString());
        user.setLastSign(day);
        user.setMostSign(mostSign);
        user.setBean(bean);

        userService.update(user);
        return new Response<>(0, "success", user);
    }

    /**
     * 获取验证码
     */
    @GetMapping("/a/u/student/code")
    public Response getCode(@RequestParam(value = "info") String info,
                            @RequestParam(value = "type") Integer type) {
        log.info("获取验证码 type = {}, info = {}", type, info);

        if(type.equals(Code.PHONE)) {
            if(!info.matches(Code.REGEX_PHONE)){
                log.info("手机号格式不正确");
                return new Response<>(-1, "手机号格式不正确", info);
            }
            try{
                Boolean result = MsgUtil.sendMsg(info);
                System.out.println(result);
                if(result){
                    return new Response<>(0, "success", info);
                }else {
                    log.info("验证码发送失败");
                    return new Response<>(-1, "发送失败", info);
                }
            }catch (IOException e){
                e.printStackTrace();
                log.info("验证码发送失败 msg = {}", e.getMessage());
                return new Response<>(-1, "发送失败", info);
            }
        }
        return new Response<>(0, "sucess", info);
    }

    /**
     * 绑定
     */
    @PutMapping("/a/u/student/bind")
    public Response bind(@RequestParam(value = "info") String info,
                         @RequestParam(value = "type") String type,
                         @RequestParam(value = "code") Integer code) {
        if(code==6666) {
            return new Response<>(0, "sucess", info);
        }else {
            return new Response<>(-1, "验证码错误", info);
        }
    }

    /**
     * 修改学生信息
     */
    @PutMapping("/a/u/student")
    public Response update(@RequestBody User user, HttpServletRequest request) {
        Long uid = LoginUtil.getUid(request);
        log.info("修改学生信息 uid = {}", uid);
        user.setId(uid);
        user.setSignDays(userService.findById(uid).getSignDays());
        userService.update(user);
        return new Response<>(0, "success", uid);
    }

    /**
     * 获取学生信息
     */
    @GetMapping("/a/u/student")
    public Response find(HttpServletRequest request) {
        Long uid = LoginUtil.getUid(request);
        log.info("获取用户信息 uid = {}", uid);
        User user = userService.findById(uid);
        return new Response<>(0, "success", user);
    }
}
