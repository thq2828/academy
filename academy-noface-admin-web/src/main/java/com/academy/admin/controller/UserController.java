package com.academy.admin.controller;

import com.academy.core.pojo.Response;
import com.academy.core.pojo.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    /**
     * 后台用户列表
     */
    @GetMapping("/a/u/user/list")
    public Response findList(@RequestParam(value = "page") Integer page,
                             @RequestParam(value = "size") Integer size,
                             @RequestParam(value = "nick", required = false) String nick,
                             @RequestParam(value = "id", required = false) Long id,
                             @RequestParam(value = "grade", required = false) Integer grade,
                             @RequestParam(value = "phone", required = false) String phone,
                             @RequestParam(value = "email", required = false) String email,
                             @RequestParam(value = "status", required = false) Integer status,
                             @RequestParam(value = "beanFrom", required = false) Integer beanFrom,
                             @RequestParam(value = "beanTo", required = false) Integer beanTo,
                             @RequestParam(value = "address", required = false) String address) {
        User user = new User();
        user.setId(1L);
        user.setOpenId("SADC123SDAD");
        user.setNick("奥巴马");
        user.setPic("http://thirdwx.qlogo.cn/mmopen/vi_32/kaCCtnsmXico9mEWKmTKeZpjibLLchNe5TTF7b9IoAAeqQoP1BnRpDuukr1DsVnFXm1zzM2tWRck8biaMUffh2DPg/132");
        user.setBean(888);
        user.setGrade(4);
        user.setStatus(1);
        user.setAddress("北京朝阳");


        List<User> userList = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            userList.add(user);
        }
        return new Response<>(0, "success", userList);
    }

    /**
     * 用户详情
     */
    @GetMapping("/a/u/user/{id}")
    public Response find(@PathVariable("id") Long id) {
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

    /**
     * 用户上下架
     */
    @PutMapping("/a/u/user/status/{id}")
    public Response updateStatus(@PathVariable("id") Long id) {
        return new Response<>(0, "success", id);
    }
}
