package com.academy.admin.controller;

import com.academy.core.dto.ResultBean;
import com.academy.core.pojo.Module;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "后台Module模块接口")
@RestController
@Slf4j
@RequestMapping("a")
public class ModuleController {
    private static Module module;
    /**
     * 初始化module假数据
     */
    static {
        module=new Module();
        module.setId(1L);
        module.setName("模块名字");
        module.setParentId(0L);
        module.setType("web");
        module.setUrl("这是模块对应的URL，前端模版路由");
        module.setCreateAt(System.currentTimeMillis());
        module.setUpdateAt(System.currentTimeMillis());
        module.setCreateBy(1L);
        module.setUpdateBy(1L);
    }

    /**
     * ----------------------------------后台module接口：拦截/a/a/*路由----------------------------
     */

    @ApiOperation(value = "模块列表",notes = "查询全部的模块列表")
    @GetMapping("/a/modules")
    public ResultBean getModules(@RequestParam(name = "page")Integer page,
                                 @RequestParam(name = "size")Integer size){
        if (page==null||page<1){
            page=1;
        }
        if (size==null||size<1){
            size=10;
        }
        List<Module> modules= new ArrayList<>();
        for (int i=0;i<size;i++){
            modules.add(module);
        }
        return new ResultBean<List<Module>>(200,modules);
    }

    @ApiOperation(value = "批量获取模块信息",notes = "根据ids=[1][2][3][4][6][8][...]获取模块id=1,2,3,4,6,8的模块信息")
    @GetMapping("/a/modules/list")
    public ResultBean getModulebyIds(@RequestParam(name = "ids")Long[] ids){
        List<Module> modules =new ArrayList<>();
        for (int i=0;i<ids.length;i++){
            modules.add(module);
        }
        return new ResultBean<List<Module>>(200,modules);
    }


    @ApiOperation(value = "模块详情",notes = "根据URL获取模块的id查询模块详情")
    @GetMapping("/a/module/{id}")
    public ResultBean getModule(@PathVariable(name = "id")Long id){
        return new ResultBean(200);
    }

    @ApiOperation(value = "新增模块",notes = "创建模块")
    @PostMapping("/a/module")
    public ResultBean addModule(@RequestBody Module module){
        return new ResultBean(200);
    }

    @ApiOperation(value = "修改模块",notes = "修改模块")
    @PutMapping("/a/module/{id}")
    public ResultBean putModule(@PathVariable(name = "id")Long id,@RequestBody Module module){
        return new ResultBean(200);
    }

    @ApiOperation(value = "删除模块",notes = "删除模块")
    @DeleteMapping("/a/module/{id}")
    public ResultBean delModule(@PathVariable(name = "id")Long id){
        return new  ResultBean(200);
    }


}
