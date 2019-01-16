package com.academy.admin.controller;

import com.academy.core.dto.PageBean;
import com.academy.core.dto.ResultBean;
import com.academy.core.pojo.Role;
import com.academy.core.service.RoleService;
import com.academy.core.util.AccessTokenUtil;
import com.academy.core.util.PageUtil;
import com.academy.core.util.PublicUtility;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;

import static com.academy.core.constant.Constant.*;

@Api(tags = "后台Role模块接口")
@RestController
@Slf4j
@RequestMapping("a")
public class RoleController {

    @Autowired
    private RoleService roleService;


    /**
     * ----------------------------------后台角色接口：拦截的是/a/a/*-------------------------------------
     */

    @ApiOperation(value = "角色详情", notes = "根据URL获取角色的id查看的角色详情")
    @GetMapping("/a/role/{id}")
    public ResultBean getRole(@PathVariable(name = "id") Long id) {
        log.info("--------------进入RoleController.getRole--------------");
        return roleService.getRole(id);
    }

    @ApiOperation(value = "角色列表", notes = "查询出角色的列表,默认：page=1，size=10")
    @GetMapping("/a/roles")
    public ResultBean getRoles(@RequestParam(name = "page", required = false) Integer page,
                               @RequestParam(name = "size", required = false) Integer size) {
        log.info("--------------进入RoleController.getRoles--------------");
        if (page == null || page < ONE) {
            page = START_PAGE;
        }
        if (size == null || size < ONE) {
            size = START_SIZE;
        }
        int start = PageUtil.getStart(page, size);
        log.info("启始数：" + start + ",每页总数：" + size);
        ResultBean resultBean = roleService.getRoles(start, size);
        if (!PublicUtility.isNullOrEmpty(resultBean.getData())) {
            ((PageBean) resultBean).setPageNum(page);
        }
        return resultBean;
    }

    @ApiOperation(value = "增加角色", notes = "创建角色，角色名不能为null，角色的权限信息是选择的模块id转化成的JSON字符串发送给我的")
    @PostMapping("/a/role")
    public ResultBean addRole(HttpServletRequest request, @RequestBody Role role) {
        log.info("--------------进入RoleController.addRole--------------");
        if (PublicUtility.isNullOrEmpty(role)) {
            return new ResultBean(661);
        }
        if (PublicUtility.strIsEmpty(role.getName())) {
            return new ResultBean(662);
        }
        if (PublicUtility.strIsEmpty(role.getStatus())) {
            role.setStatus(STATUS);
        }
        Long managerId = Long.valueOf(AccessTokenUtil.getAccessTokeValues(request, MANAGER_ID).toString());
        role.setCreateBy(managerId);
        role.setUpdateBy(managerId);
        role.setUpdateAt(System.currentTimeMillis());
        role.setCreateAt(System.currentTimeMillis());
        log.info(role.toString());

        return roleService.addRole(role);
    }

    @ApiOperation(value = "修改角色", notes = "修改角色")
    @PutMapping("/a/role/{id}")
    public ResultBean putRole(HttpServletRequest request, @PathVariable(name = "id") Long id, @RequestBody Role role) {
        log.info("--------------进入RoleController.putRole--------------");
        if (PublicUtility.isNullOrEmpty(role)) {
            return new ResultBean(661);
        }
        Long managerId = Long.valueOf(AccessTokenUtil.getAccessTokeValues(request, MANAGER_ID).toString());
        role.setId(id);
        role.setUpdateBy(managerId);
        role.setUpdateAt(System.currentTimeMillis());
        log.info(role.toString());
        return roleService.putRole(role);
    }

    @ApiOperation(value = "删除角色", notes = "删除角色")
    @DeleteMapping("/a/role/{id}")
    public ResultBean delRole(@PathVariable(name = "id") Long id) {
        log.info("--------------进入RoleController.delRole--------------");
        return roleService.delRole(id);
    }

    @ApiOperation(value = "批量获取角色详细信息", notes = "根据ids=[1][2][3][4][6][8][...]获取角色id=1,2,3,4,6,8的角色信息")
    @GetMapping("/a/roles/list")
    public ResultBean getRolesByIds(@RequestParam(name = "ids") Long[] ids,
                                    @RequestParam(name = "page") Integer page,
                                    @RequestParam(name = "size") Integer size) {
        log.info("--------------进入RoleController.getRolesByIds--------------");
        if (ids==null||ids.length <= ONE) {
            return new ResultBean(663);
        }
        if (page == null || page < ONE) {
            page = START_PAGE;
        }
        if (size == null || size < ONE) {
            size = START_SIZE;
        }
        int start = PageUtil.getStart(page, size);
        log.info(Arrays.toString(ids));
        return roleService.getRolesByIds(ids, start, size);
    }
}
