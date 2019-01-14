package com.academy.core.service;

import com.academy.core.dto.PageBean;
import com.academy.core.dto.ResultBean;
import com.academy.core.pojo.Role;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Role业务层接口
 */
@FeignClient(value = "admin-service")
public interface RoleService {

    /**
     * 根据id获取角色详细
     * @param id
     * @return
     */
    @GetMapping("/impl/role")
    public ResultBean getRole(@RequestParam(name = "id") Long id);


    /**
     * 获取角色列表
     * @param start
     * @param size
     * @return
     */
    @GetMapping("/impl/roles")
    public PageBean getRoles(@RequestParam(name = "start")Integer start,
                             @RequestParam(name = "size") Integer size);

    /**
     * 根据ids获取角色列表
     * @param ids
     * @param start
     * @param size
     * @return
     */
    @GetMapping("/impl/roles/list")
    public PageBean getRolesByIds(@RequestParam(name = "ids")Long[] ids,
                                  @RequestParam(name = "start")Integer start,
                                  @RequestParam(name = "size") Integer size);

    /**
     * 增加一个角色
     * @param role
     * @return
     */
    @PostMapping("/impl/role")
    public ResultBean addRole(@RequestBody Role role);

    /**
     * 更新一个角色
     * @param role
     * @return
     */
    @PutMapping("/impl/role")
    public ResultBean putRole(@RequestBody Role role);

    /**
     * 删除一个角色
     * @param id
     * @return
     */
    @DeleteMapping("/impl/role")
    public ResultBean delRole(@RequestParam(name = "id") Long id);
}
