package com.academy.home.controller;

import com.academy.core.pojo.Response;
import com.academy.core.pojo.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    /**
     * 获取签到详情
     */
    @GetMapping("/a/u/student/sign")
    public Response getSign() {
        User user = new User();
        user.setId(1L);
        user.setOpenId("SADC123SDAD");
        user.setNick("奥巴马");
        user.setPic("http://thirdwx.qlogo.cn/mmopen/vi_32/kaCCtnsmXico9mEWKmTKeZpjibLLchNe5TTF7b9IoAAeqQoP1BnRpDuukr1DsVnFXm1zzM2tWRck8biaMUffh2DPg/132");
        user.setBean(888);
        user.setGrade(4);
        user.setStatus(1);
        user.setAddress("北京朝阳");
        user.setSignDays("[1, 5, 6, 7, 8, 20, 22]");
        user.setLastSign(22);
        user.setNowSign(1);
        user.setMostSign(4);

        return new Response<>(0, "success", user);
    }

    /**
     * 点击签到
     */
    @PutMapping("/a/u/student/sign")
    public Response sign() {
        User user = new User();
        user.setId(1L);
        user.setOpenId("SADC123SDAD");
        user.setNick("奥巴马");
        user.setPic("http://thirdwx.qlogo.cn/mmopen/vi_32/kaCCtnsmXico9mEWKmTKeZpjibLLchNe5TTF7b9IoAAeqQoP1BnRpDuukr1DsVnFXm1zzM2tWRck8biaMUffh2DPg/132");
        user.setBean(888);
        user.setGrade(4);
        user.setStatus(1);
        user.setAddress("北京朝阳");
        user.setSignDays("[1, 5, 6, 7, 8, 20, 22, 23]");
        user.setLastSign(23);
        user.setNowSign(2);
        user.setMostSign(4);

        return new Response<>(0, "success", user);
    }

    /**
     * 获取验证码
     */
    @GetMapping("/a/u/student/code")
    public Response getCode(@RequestParam(value = "info") String info,
                            @RequestParam(value = "type") String type) {

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
    public Response update(@RequestBody User user) {

        return new Response<>(0, "success", 1L);
    }

    /**
     * 获取学生信息
     */
    @GetMapping("/a/u/student")
    public Response find() {
        User user = new User();
        user.setId(1L);
        user.setOpenId("SADC123SDAD");
        user.setNick("奥巴马");
        user.setPic("http://thirdwx.qlogo.cn/mmopen/vi_32/kaCCtnsmXico9mEWKmTKeZpjibLLchNe5TTF7b9IoAAeqQoP1BnRpDuukr1DsVnFXm1zzM2tWRck8biaMUffh2DPg/132");
        user.setBean(888);
        user.setGrade(4);
        user.setStatus(1);
        user.setAddress("北京朝阳");

        return new Response<>(0, "success", user);
    }




}
