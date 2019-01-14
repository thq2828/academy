package com.academy.admin.service.impl;

import com.academy.admin.client.AuthServiceClient;
import com.academy.admin.dao.ManagerMapper;
import com.academy.core.dto.PageBean;
import com.academy.core.service.ManagerService;
import com.academy.core.dto.ResultBean;
import com.academy.core.pojo.JWT;
import com.academy.core.pojo.Manager;
import com.academy.core.util.BPwdEncoderUtils;
import com.academy.core.util.PublicUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.academy.core.constant.Constant.*;


/**
 * 服务向外面暴露接口
 */

@RestController
@Slf4j
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    AuthServiceClient authServiceClient;

    @Override
    @GetMapping("/impl/manager")
    public ResultBean getManagerInfo(@RequestParam(name = "name") String username,
                                     @RequestParam(name = "pwd") String password) {
        log.info("-------------进入ManagerServiceImpl.getManagerInfo-----------");
        log.info("name:" + username + " 密码：" + password);
        Manager manager = managerMapper.selectByName(username);
        if (PublicUtility.isNullOrEmpty(manager)) {
            return new ResultBean(622);
        }
        if (!BPwdEncoderUtils.matches(password, manager.getPwd())) {
            return new ResultBean(623);
        }
        JWT jwt = authServiceClient.getToken("Basic dWFhLXNlcnZpY2U6MTIzNDU2Nzg5", "password", username, password);
        // 获得用户菜单
        if (PublicUtility.isNullOrEmpty(jwt)) {
            return new ResultBean(624);
        }
        return new ResultBean<JWT>(200, jwt);
    }

    @Override
    @GetMapping("/impl/managers")
    public PageBean getManagers(@RequestParam(name = "name", required = false) String name,
                                @RequestParam(name = "roleId", required = false) Long roleId,
                                @RequestParam(name = "start") Integer start,
                                @RequestParam(name = "size") Integer size) {
        log.info("-------------进入ManagerServiceImpl.getManagers-----------");
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("roleId", roleId);
        int totalRecord = managerMapper.selectByCountSelective(map);
        if (totalRecord < ONE) {
            return new PageBean(625);
        }
        map.put("start", start);
        map.put("size", size);
        List<Manager> managers = managerMapper.selectByPrimaryKeySelective(map);
        return new PageBean<List<Manager>>(200, size, totalRecord, managers);
    }

    @Override
    @GetMapping("/impl/manager/{id}")
    public ResultBean getManageById(@PathVariable(name = "id") Long id) {
        log.info("----------------------进入进入ManagerServiceImpl.getManagers-------------------");
        log.info("id:" + id);
        Manager manager = managerMapper.selectByPrimaryKey(id);
        if (PublicUtility.isNullOrEmpty(manager)) {
            return new ResultBean(625);
        }
        manager.setPwd("************");
        return new ResultBean<Manager>(200, manager);
    }

    @PostMapping("/impl/manager")
    @Override
    public ResultBean addManager(@RequestBody Manager manager) {
        log.info("----------------------进入进入ManagerServiceImpl.addManager-------------------");
        log.info("ManagerServiceImpl.addManager:manager:" + manager);
        Manager manager1 = managerMapper.selectByName(manager.getName());
        if (!PublicUtility.isNullOrEmpty(manager1)) {
            return new ResultBean(629);
        }
        int i = managerMapper.insert(manager);
        if (i < MANAGER_SUCCESS) {
            log.info("ManagerServiceImpl.addManager:新增manager失败！");
            return new ResultBean(204);
        }
        return new ResultBean(200);
    }


    @PutMapping("/impl/manager")
    @Override
    public ResultBean putManager(@RequestBody Manager manager) {
        log.info("----------------------进入进入ManagerServiceImpl.putManager-------------------");
        if (!PublicUtility.strIsEmpty(manager.getName())){
            Manager manager1 =managerMapper.selectByName(manager.getName());
            if (!PublicUtility.isNullOrEmpty(manager1)){
                return new ResultBean(629);
            }
        }
        int i=managerMapper.updateByPrimaryKeySelective(manager);
        if(i<MANAGER_PUT_STATUS){
            log.info("ManagerServiceImpl.putManager:更新失败");
            return new ResultBean(631);
        }
        return new ResultBean(200);
    }



    @DeleteMapping("/impl/manager/{id}")
    @Override
    public ResultBean delManager(@PathVariable(name = "id") Long id) {
        log.info("----------------------进入进入ManagerServiceImpl.delmanager-------------------");
        log.info(id.toString());
        int i = managerMapper.deleteByPrimaryKey(id);
        if (i < MANAGER_DEL_SUCCESS) {
            log.info("ManagerServiceImpl.delmanager:删除manager失败");
            return new ResultBean(204);
        }
        return new ResultBean(200);
    }

    @PutMapping("/impl/pwd")
    @Override
    public ResultBean putPwd(@RequestBody Manager manager) {
        log.info("----------------------进入进入ManagerServiceImpl.putPwd-------------------");
        log.info("更新为：" + manager.toString());

        int i = managerMapper.updateByPrimaryKeySelective(manager);
        if (i < MANAGER_PUT_PWD) {
            return new ResultBean(631);
        }
        return new ResultBean(200);
    }
}
