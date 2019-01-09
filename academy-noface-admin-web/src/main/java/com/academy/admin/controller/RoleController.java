package com.academy.admin.controller;

import com.academy.core.dto.ResultBean;
import com.academy.core.pojo.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "后台Role模块接口")
@RestController
@Slf4j
@RequestMapping("a")
public class RoleController {

    /**
     * 初始化Role假数据
     */
    private static Role role;

    static {
        role = new Role();
        role.setId(1L);
        role.setName("角色名字");
        role.setStatus("用户状态");
        role.setPermissions("{[1],[2],[3],[4],[5].....}");
        role.setCreateAt(System.currentTimeMillis());
        role.setUpdateAt(System.currentTimeMillis());
        role.setCreateBy(1L);
        role.setUpdateBy(1L);
    }

    /**
     * ----------------------------------后台角色接口：拦截的是/a/a/*-------------------------------------
     */

    @ApiOperation(value = "角色详情", notes = "根据URL获取角色的id查看的角色详情")
    @GetMapping("/a/role/{id}")
    public ResultBean getRole(@PathVariable(name = "id") Long id) {
        return new ResultBean<Role>(200, role);
    }

    @ApiOperation(value = "角色列表", notes = "查询出角色的列表")
    @GetMapping("/a/roles")
    public ResultBean getRoles(@RequestParam(name = "page") Integer page,
                               @RequestParam(name = "size") Integer size) {
        if (page == null || page < 1) {
            page = 1;
        }
        if (size == null || size < 1) {
            size = 10;
        }
        List<Role> roles = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            roles.add(role);
        }
        return new ResultBean<List<Role>>(200, roles);
    }

    @ApiOperation(value = "增加角色", notes = "创建角色")
    @PostMapping("/a/role")
    public ResultBean addRole(@RequestBody Role role) {
        return new ResultBean(200);
    }

    @ApiOperation(value = "修改角色", notes = "修改角色")
    @PutMapping("/a/role/{id}")
    public ResultBean putRole(@PathVariable(name = "id") Long id, @RequestBody Role role) {
        return new ResultBean(200);
    }

    @ApiOperation(value = "删除角色", notes = "删除角色")
    @DeleteMapping("/a/role/{id}")
    public ResultBean delRole(@PathVariable(name = "id") Long id) {
        return new ResultBean(200);
    }

    @ApiOperation(value = "批量获取角色详细信息", notes = "根据ids=[1][2][3][4][6][8][...]获取角色id=1,2,3,4,6,8的角色信息")
    @GetMapping("/a/roles/list")
    public ResultBean getRolesByIds(@RequestParam(name = "ids") Long[] ids) {
        List<Role> roles = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            roles.add(role);
        }
        return new ResultBean<List<Role>>(200,  roles);
    }

}
