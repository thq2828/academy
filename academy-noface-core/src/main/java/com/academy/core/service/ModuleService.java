package com.academy.core.service;

import com.academy.core.dto.PageBean;
import com.academy.core.dto.ResultBean;
import com.academy.core.pojo.Module;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Module模块业务接口i
 */
@FeignClient(value = "admin-service")
public interface ModuleService {
    /**
     * 获取模块列表
     * @param start
     * @param size
     * @return
     */
    @GetMapping("/impl/modules")
    public PageBean getModules(@RequestParam(name = "start")Integer start,
                               @RequestParam(name = "size")Integer size);


    @GetMapping("/impl/modules/list")
    public PageBean getModulesByIds(@RequestParam(name = "ids")Long[] ids,
                                    @RequestParam(name = "start")Integer start,
                                    @RequestParam(name = "size")Integer size);
    /**
     * 获取模块的详情
     * @param id
     * @return
     */
    @GetMapping("/impl/module")
    public ResultBean getModule(@RequestParam(name = "id")Long id);

    /**
     * 增加一个模块
     * @param module
     * @return
     */
    @PostMapping("/impl/module")
    public ResultBean addModule(@RequestBody Module module);

    /**
     * 动态更新一个模块
     * @param module
     * @return
     */
    @PutMapping("/impl/module")
    public ResultBean putModule(@RequestBody Module module);

    /**
     * 根据id删除一个模块
     * @param id
     * @return
     */
    @DeleteMapping("/impl/module")
    public ResultBean delModule(@RequestParam(name = "id")Long id);

}
