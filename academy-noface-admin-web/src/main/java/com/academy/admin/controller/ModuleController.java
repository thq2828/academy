package com.academy.admin.controller;

import com.academy.core.dto.PageBean;
import com.academy.core.dto.ResultBean;
import com.academy.core.pojo.Module;
import com.academy.core.service.ModuleService;
import com.academy.core.util.AccessTokenUtil;
import com.academy.core.util.JsonUtil;
import com.academy.core.util.PageUtil;
import com.academy.core.util.PublicUtility;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.academy.core.constant.Constant.*;

@Api(tags = "后台Module模块接口")
@RestController
@Slf4j
@RequestMapping("a")
public class ModuleController {
    @Autowired
    private ModuleService moduleService;


    /**
     * ----------------------------------后台module接口：拦截/a/a/*路由----------------------------
     */

    @ApiOperation(value = "模块列表", notes = "查询全部的模块列表.默认：page=1，size=10")
    @GetMapping("/a/modules")
    public ResultBean getModules(@RequestParam(name = "page",required = false) Integer page,
                                 @RequestParam(name = "size",required = false) Integer size) {
        log.info("--------------进入ModuleController.getRoles--------------");
        if (page == null || page < ONE) {
            page = START_PAGE;
        }
        if (size == null || size < ONE) {
            size = START_SIZE;
        }
        int start = PageUtil.getStart(page, size);
        log.info("启始数：" + start + ",每页总数：" + size);
        ResultBean resultBean = moduleService.getModules(start, size);
        if (!PublicUtility.isNullOrEmpty(resultBean.getData())) {
            ((PageBean) resultBean).setPageNum(page);
        }
        return resultBean;
    }

    @ApiOperation(value = "批量获取模块信息", notes = "根据ids=[1][2][3][4][6][8][...]获取模块id=1,2,3,4,6,8的模块信息。默认：page=1，size=10")
    @GetMapping("/a/modules/list")
    public ResultBean getModulebyIds(@RequestParam(name = "ids", required = false) Long[] ids,
                                     @RequestParam(name = "page", required = false) Integer page,
                                     @RequestParam(name = "size", required = false) Integer size,
                                     HttpServletRequest request) {
        log.info("--------------进入ModuleController.getRolesByIds--------------");
        //获取用户的权限信息
        if (ids == null || ids.length < 1) {
            Object permissions = AccessTokenUtil.getAccessTokeValues(request, AUTHORITIES);
            if (PublicUtility.isNullOrEmpty(permissions)) {
                return new ResultBean(205);
            }
            List id1 = JsonUtil.toObject(permissions.toString(), List.class);
            for (Object id2 : id1) {
                ids = JsonUtil.toObject(id2.toString(), Long[].class);
            }
        }
        if (page == null || page < ONE) {
            page = START_PAGE;
        }
        if (size == null || size < ONE) {
            size = START_SIZE;
        }

        int start = PageUtil.getStart(page, size);
        log.info(Arrays.toString(ids));
        ResultBean resultBean=moduleService.getModulesByIds(ids, start, size);
        if (!PublicUtility.isNullOrEmpty(resultBean.getData())){
            ((PageBean) resultBean).setPageNum(page);
        }
        return resultBean;
    }


    @ApiOperation(value = "模块详情", notes = "根据URL获取模块的id查询模块详情")
    @GetMapping("/a/module/{id}")
    public ResultBean getModule(@PathVariable(name = "id") Long id) {
        log.info("--------------进入ModuleController.getModule--------------");
        log.info("id:" + id);
        return moduleService.getModule(id);
    }

    @ApiOperation(value = "新增模块", notes = "创建模块")
    @PostMapping("/a/module")
    public ResultBean addModule(HttpServletRequest request,
                                @RequestBody Module module) {
        log.info("--------------进入ModuleController.addModule--------------");
        if (PublicUtility.isNullOrEmpty(module)) {
            return new ResultBean(641);
        }
        if (PublicUtility.strIsEmpty(module.getName())) {
            return new ResultBean(642);
        }
        if (PublicUtility.numIsEmpty(module.getParentId())) {
            module.setParentId(PARENT);
        }
        if (PublicUtility.strIsEmpty(module.getType())) {
            module.setType(TYPE);
        }
        Long managerId = Long.valueOf(AccessTokenUtil.getAccessTokeValues(request, MANAGER_ID).toString());
        module.setUpdateBy(managerId);
        module.setCreateBy(managerId);
        module.setCreateAt(System.currentTimeMillis());
        module.setUpdateAt(System.currentTimeMillis());
        log.info(module.toString());
        return moduleService.addModule(module);
    }


    @ApiOperation(value = "修改模块", notes = "修改模块")
    @PutMapping("/a/module/{id}")
    public ResultBean putModule(HttpServletRequest request,
                                @PathVariable(name = "id") Long id,
                                @RequestBody Module module) {
        log.info("--------------进入ModuleController.putModule--------------");
        if (PublicUtility.isNullOrEmpty(module)) {
            return new ResultBean(641);
        }
        Long managerId = Long.valueOf(AccessTokenUtil.getAccessTokeValues(request, MANAGER_ID).toString());
        module.setId(id);
        module.setUpdateBy(managerId);
        module.setUpdateAt(System.currentTimeMillis());
        log.info(module.toString());
        return moduleService.putModule(module);

    }


    @ApiOperation(value = "删除模块", notes = "删除模块")
    @DeleteMapping("/a/module/{id}")
    public ResultBean delModule(@PathVariable(name = "id") Long id) {
        log.info("--------------进入ModuleController.delModule--------------");
        log.info("id:" + id);
        return moduleService.delModule(id);
    }


}
