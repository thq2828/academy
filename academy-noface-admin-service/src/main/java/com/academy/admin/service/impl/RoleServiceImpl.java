package com.academy.admin.service.impl;

import com.academy.admin.dao.ManagerMapper;
import com.academy.admin.dao.RoleMapper;
import com.academy.core.dto.PageBean;
import com.academy.core.dto.ResultBean;
import com.academy.core.pojo.Manager;
import com.academy.core.pojo.Role;
import com.academy.core.service.RoleService;
import com.academy.core.util.PublicUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.academy.core.constant.Constant.*;


@RestController
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private ManagerMapper managerMapper;

    @GetMapping("/impl/role")
    @Override
    public ResultBean getRole(@RequestParam(name = "id") Long id) {
        log.info("----------------------进入进入RoleServiceImpl.getRole-------------------");
        log.info("id:" + id);
        Role role = roleMapper.selectByPrimaryKey(id);
        if (PublicUtility.isNullOrEmpty(role)) {
            return new ResultBean(665);
        }
        return new ResultBean<Role>(200, role);
    }

    @GetMapping("/impl/roles")
    @Override
    public PageBean getRoles(@RequestParam(name = "start") Integer start,
                             @RequestParam(name = "size") Integer size) {
        log.info("----------------------进入进入RoleServiceImpl.getRoles-------------------");
        log.info("start:" + start + ",size:" + size);
        //取出总条数判断是否有数据
        int totalRecord = roleMapper.selecrByCount();
        if (totalRecord < ONE) {
            return new PageBean(665);
        }

        List<Role> roles = roleMapper.selectRoles(start, size);
        return new PageBean<List<Role>>(200, size, totalRecord, roles);
    }

    @GetMapping("/impl/roles/list")
    @Override
    public PageBean getRolesByIds(@RequestParam(name = "ids") Long[] ids,
                                  @RequestParam(name = "start") Integer start,
                                  @RequestParam(name = "size") Integer size) {
        log.info("----------------------进入进入RoleServiceImpl.getRolesByIds-------------------");
        log.info("ids:" + ids + ",start:" + start + ",size:" + size);
        Map<String, Object> map = new HashMap<>();
        map.put("ids", ids);
        List<Role> roles = roleMapper.selectByIds(map);
        if (PublicUtility.isNullOrEmpty(roles)){
            return new PageBean(665);
        }
        int totalRecord = roles.size();
        //判断总数是否小于或者等于size，如果是不用在去查了
        if (totalRecord <= size) {
            log.info("判断总数是否小于或者等于size，如果是不用在去查了");
            return new PageBean<List<Role>>(200, size, totalRecord, roles);
        }
        map.put("start", start);
        map.put("size", size);
        List<Role> roless = roleMapper.selectByIds(map);
        return new PageBean<List<Role>>(200, size, totalRecord, roless);
    }

    @PostMapping("/impl/role")
    @Override
    public ResultBean addRole(@RequestBody Role role) {
        log.info("----------------------进入进入RoleServiceImpl.addRole-------------------");
        log.info(role.toString());
        //判断这个角色名是否存在
        Role role1 = roleMapper.selectByName(role.getName());
        if (!PublicUtility.isNullOrEmpty(role1)) {
            return new ResultBean(666);
        }
        int i = roleMapper.insert(role);
        if (i < ROLE_SUCCESS) {
            log.info("role保存失败！");
            return new ResultBean(667);
        }
        return new ResultBean(200);
    }

    @PutMapping("/impl/role")
    @Override
    public ResultBean putRole(@RequestBody Role role) {
        log.info("----------------------进入进入RoleServiceImpl.putRole-------------------");
        //判断这个角色名是否存在
        Role role1 = roleMapper.selectByName(role.getName());
        if (!PublicUtility.isNullOrEmpty(role1)) {
            return new ResultBean(666);
        }
        int i = roleMapper.updateByPrimaryKeySelective(role);
        if (i < ROLE_PUT) {
            log.info("更新失败！");
            return new ResultBean(668);
        }
        return new ResultBean(200);
    }

    @DeleteMapping("/impl/role")
    @Override
    public ResultBean delRole(@RequestParam(name = "id") Long id) {
        log.info("----------------------进入进入RoleServiceImpl.delRole-------------------");
        log.info("id:" + id);
        //查询roleId下是否有管理员
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", id);
        List<Manager> managers = managerMapper.selectByPrimaryKeySelective(map);
        //判读Manager是否为null
        if (!PublicUtility.isNullOrEmpty(managers)) {
            return new ResultBean(669);
        }
        //执行删除操作
        int i = roleMapper.deleteByPrimaryKey(id);
        if (i < ROLE_DEl) {
            return new ResultBean(670);
        }
        return new ResultBean(200);
    }
}
