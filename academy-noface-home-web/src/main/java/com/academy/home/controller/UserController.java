package com.academy.home.controller;

import com.academy.core.pojo.Code;
import com.academy.core.pojo.Response;
import com.academy.core.pojo.User;
import com.academy.core.service.CodeService;
import com.academy.core.service.UserService;
import com.academy.core.util.UploadPicUtil;
import com.academy.home.utils.EmailUtil;
import com.academy.home.utils.LoginUtil;
import com.academy.home.utils.MsgUtil;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.print.attribute.standard.NumberUp;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private CodeService codeService;

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
                Integer result = MsgUtil.sendMsg(info);
                if(result==null){
                    log.info("验证码发送失败");
                    return new Response<>(-1, "发送失败", info);
                }else {
                    Code code = new Code();
                    code.setInfo(info);
                    code.setNumber(result);
                    codeService.insertCode(code);
                    return new Response<>(0, "success", info);
                }
            }catch (IOException e){
                e.printStackTrace();
                log.info("验证码发送失败 msg = {}", e.getMessage());
                return new Response<>(-1, "发送失败", info);
            }
        }else if(type.equals(Code.EMAIL)) {
            if(!info.matches(Code.REGEX_EMAIL)){
                log.info("邮箱格式不正确");
                return new Response<>(-1, "邮箱格式不正确", info);
            }
            try{
                Integer result = EmailUtil.sendMsg(info);
                if(result==null){
                    log.info("验证码发送失败");
                    return new Response<>(-1, "发送失败", info);
                }else {
                    Code code = new Code();
                    code.setInfo(info);
                    code.setNumber(result);
                    codeService.insertCode(code);
                    return new Response<>(0, "success", info);
                }
            }catch (Exception e){
                e.printStackTrace();
                log.info("验证码发送失败 msg = {}", e.getMessage());
                return new Response<>(-1, "发送失败", info);
            }
        }else {
            log.info("非法type = {}", type);
            return new Response<>(-1, "非法type", type);
        }
    }

    /**
     * 绑定
     */
    @PutMapping("/a/u/student/bind")
    public Response bind(@RequestParam(value = "info") String info,
                         @RequestParam(value = "type") Integer type,
                         @RequestParam(value = "code") Integer code,
                         HttpServletRequest request) {
        Long uid = LoginUtil.getUid(request);
        log.info("用户绑定 info = {}, type = {}, code = {}, uid = {}", info, type, code, uid);
        Code check = codeService.findByInfo(info);
        if(check==null){
            log.info("绑定信息错误");
            return new Response<>(-1, "绑定信息错误", info);
        }
        if(System.currentTimeMillis() - check.getUpdateAt() > Code.TEN_MINUTES) {
            log.info("验证码过期");
            return new Response<>(-1, "验证码过期", info);
        }
        if(!code.equals(check.getNumber())) {
            log.info("验证码错误");
            return new Response<>(-1, "验证码错误", info);
        }
        User user = userService.findById(uid);
        if(type.equals(Code.EMAIL)){
            user.setEmail(info);
            userService.update(user);
            log.info("更新用户 email = {}", info);
        }
        if(type.equals(Code.PHONE)){
            user.setPhone(info);
            userService.update(user);
            log.info("更新用户 phone = {}", info);
        }
        return new Response<>(0, "success", uid);
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

    @PostMapping("/a/u/student/pic")
    public Response upload(@RequestParam(value = "pic") MultipartFile pic, HttpServletRequest request) {
        try{
            String[] fileArray = Objects.requireNonNull(pic.getOriginalFilename()).split("[.]");
            String picSuff = fileArray[fileArray.length - 1];
            if(!picSuff.toUpperCase().equals(User.PIC_PNG) && !picSuff.toUpperCase().equals(User.PIC_JPG)) {
                log.info("图片格式不正确");
                return new Response<>(-1, "图片格式不正确", picSuff);
            }
            String result = UploadPicUtil.upload(pic.getInputStream(), User.USR_PIC_PATH, picSuff);
            return new Response<>(0, "success", result);
        }catch (IOException e) {
            log.info("上传图片失败");
            return new Response<>(-1, "上传图片失败", null);
        }
    }
}
