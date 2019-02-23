package com.academy.admin.controller;

import com.academy.core.pojo.Response;
import com.academy.core.pojo.User;
import com.academy.core.service.UserService;
import com.academy.core.util.AccessTokenUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.academy.core.constant.Constant.MANAGER_ID;


@RestController
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 后台用户列表
     */
    @GetMapping("/a/u/user/list")
    public Response findList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             @RequestParam(value = "nick", required = false) String nick,
                             @RequestParam(value = "id", required = false) Long id,
                             @RequestParam(value = "grade", required = false) Integer grade,
                             @RequestParam(value = "phone", required = false) String phone,
                             @RequestParam(value = "email", required = false) String email,
                             @RequestParam(value = "status", required = false) Integer status,
                             @RequestParam(value = "beanFrom", required = false) Integer beanFrom,
                             @RequestParam(value = "beanTo", required = false) Integer beanTo,
                             @RequestParam(value = "address", required = false) String address) {

        log.info("后台查询用户列表nick={},id={},grade={},phone={},email={},status={},beanFrom={},beanTo={},address={}",
                nick, id, grade, phone, email, status, beanFrom, beanTo, address);

        PageHelper.startPage(page, size);
        List<User> userList = userService.listUserByQuery(nick, id, grade, phone, email, status, beanFrom, beanTo, address);
        PageInfo pageInfo = new PageInfo<>(userList);

        log.info("用户列表 size = {}", userList.size());

        return new Response<>(0, "success", pageInfo);
    }

    /**
     * 用户详情
     */
    @GetMapping("/a/u/user/{id}")
    public Response find(@PathVariable("id") Long id) {
        log.info("查询用户详情 id = {}", id);
        User user = userService.findById(id);
        return new Response<>(0, "success", user);
    }

    /**
     * 用户上下架
     */
    @PutMapping("/a/u/user/status/{id}")
    public Response updateStatus(@PathVariable("id") Long id, HttpServletRequest request) {
         Long uid = Long.valueOf(AccessTokenUtil.getAccessTokeValues(request, MANAGER_ID).toString());
        log.info("用户上下架 id = {}, uid = {}", id, uid);
        User user = userService.findById(id);
        if(user == null) {
            log.info("无效id");
            return new Response<>(-1, "无效id", id);
        }
        user.setStatus(user.getStatus().equals(User.ON) ? User.OFF : User.ON);
        user.setUpdateBy(uid);
        userService.update(user);
        log.info("更新status成功 id = {}", id);
        return new Response<>(0, "success", id);
    }
}
