package com.academy.core.service;

import com.academy.core.dto.PageBean;

import com.academy.core.dto.ResultBean;
import com.academy.core.pojo.Manager;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * manager业务接口
 */
@FeignClient(value = "admin-service")
public interface ManagerService {
    /**
     * 根据姓名获取信息
     * @param name
     * @return
     */
    @GetMapping("/impl/manager")
   public ResultBean getManagerInfo(@RequestParam(name = "name") String name,
                                @RequestParam(name = "pwd")String pwd);

    /**
     * 根据传入的条件不同查询数据
     * @param name
     * @param roleId
     * @param start
     * @param size
     * @return
     */
    @GetMapping("/impl/managers")
    public PageBean getManagers(@RequestParam(name = "name",required = false)String name,
                                @RequestParam(name = "roleId",required = false)Long roleId,
                                @RequestParam(name = "start")Integer start,
                                @RequestParam(name = "size")Integer size);

    /**
     * 获取manager的详情
     * @param id
     * @return
     */
    @GetMapping("/impl/manager/{id}")
    public ResultBean getManageById(@PathVariable(name = "id")Long id);

    /**
     * 增加一个管理员
     * @param manager
     * @return
     */
    @PostMapping("/impl/manager")
    public ResultBean addManager(@RequestBody Manager manager);

    /**
     * 删除一个管理员
     * @param id
     * @return
     */
    @DeleteMapping ("/impl/manager/{id}")
    public ResultBean delManager(@PathVariable(name = "id")Long id);

    /**
     * 更新manager的数据
     * @param manager
     * @return
     */
    @PutMapping("/impl/manager")
    public ResultBean putManager(@RequestBody Manager manager);

    /**
     * 更新密码
     * @param manager
     * @return
     */
    @PutMapping("/impl/pwd")
    public ResultBean putPwd(@RequestBody Manager manager);
}
