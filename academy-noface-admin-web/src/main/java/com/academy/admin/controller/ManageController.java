package com.academy.admin.controller;

import com.academy.core.pojo.Manager;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "这个controller主要是后台文章的接口")
@RestController
@Slf4j
@RequestMapping("a")
public class ManageController {
    private static Manager manager;
    static {
        manager=new Manager();
        manager.setId(1L);
        manager.setName("admin");
    }
}
