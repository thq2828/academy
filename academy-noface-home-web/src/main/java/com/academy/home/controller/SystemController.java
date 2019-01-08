package com.academy.home.controller;

import com.academy.core.pojo.Response;
import com.academy.core.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
public class SystemController {

    @PostMapping("/a/login")
    public Response login(String code, HttpServletResponse response) {
        User user = new User();
        user.setId(1L);
        user.setOpenId("SADC123SDAD");
        user.setNick("奥巴马");
        user.setPic("http://thirdwx.qlogo.cn/mmopen/vi_32/kaCCtnsmXico9mEWKmTKeZpjibLLchNe5TTF7b9IoAAeqQoP1BnRpDuukr1DsVnFXm1zzM2tWRck8biaMUffh2DPg/132");
        user.setBean(888);
        user.setGrade(4);
        user.setStatus(1);
        user.setAddress("北京朝阳");

        response.setHeader("Authorization", "eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjEsImV4cCI6MTU0Njk1NDUwMn0.CLUcOAt6DIhdlFgm54qFGu-1QqVGmagNahGPA8axLqQ");

        return new Response<>(0, "success", user);
    }
}
