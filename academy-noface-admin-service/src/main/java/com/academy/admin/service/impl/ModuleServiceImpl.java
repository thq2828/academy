package com.academy.admin.service.impl;

import com.academy.admin.dao.ModuleMapper;
import com.academy.core.dto.PageBean;
import com.academy.core.dto.ResultBean;
import com.academy.core.pojo.Module;
import com.academy.core.pojo.Role;
import com.academy.core.service.ModuleService;
import com.academy.core.util.PublicUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.academy.core.constant.Constant.*;

@RestController
@Slf4j
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleMapper moduleMapper;

    @Override
    public PageBean getModules(@RequestParam(name = "start") Integer start,
                               @RequestParam(name = "size") Integer size) {
        log.info("----------------------ModuleServiceImpl.getModules-------------------");
        log.info("start:" + start + ",size:" + size);
        Map<String, Object> map = new HashMap<>();
        List<Module> modules = moduleMapper.selectModules(map);
        if (PublicUtility.isNullOrEmpty(modules)) {
            return new PageBean(643);
        }
        int totalRecord = modules.size();
        if (totalRecord <= size && start == ZERO) {
            log.info("totalRecord<size,start==0");
            return new PageBean<List<Module>>(200, start, size, modules);
        }
        map.put("start", start);
        map.put("size", size);
        modules = moduleMapper.selectModules(map);
        return new PageBean<List<Module>>(200, size, totalRecord, modules);
    }

    @Override
    public PageBean getModulesByIds(@RequestParam(name = "ids") Long[] ids,
                                    @RequestParam(name = "start") Integer start,
                                    @RequestParam(name = "size") Integer size) {
        log.info("----------------------进入进入ModuleServiceImpl.getModulesByIds-------------------");
        log.info("ids:" + ids + ",start:" + start + ",size:" + size);
        Map<String, Object> map = new HashMap<>();
        map.put("ids", ids);
        List<Module> modules = moduleMapper.selectByIds(map);
        if (PublicUtility.isNullOrEmpty(modules)) {
            return new PageBean(643);
        }
        int totalRecord = modules.size();
        //判断总数是否小于或者等于size，如果是不用在去查了
        if (totalRecord <= size) {
            log.info("判断总数是否小于或者等于size，如果是不用在去查了");
            return new PageBean<List<Module>>(200, size, totalRecord, modules);
        }
        map.put("start", start);
        map.put("size", size);
        modules = moduleMapper.selectByIds(map);
        return new PageBean<List<Module>>(200, size, totalRecord, modules);

    }

    @Override
    public ResultBean getModule(@RequestParam(name = "id") Long id) {
        log.info("----------------------进入进入ModuleServiceImpl.getModule-------------------");
        log.info("id:" + id);
        Module module = moduleMapper.selectByPrimaryKey(id);
        if (PublicUtility.isNullOrEmpty(module)) {
            return new ResultBean(643);
        }
        return new ResultBean<Module>(200, module);
    }

    @Override
    public ResultBean addModule(@RequestBody Module module) {
        log.info("----------------------进入进入ModuleServiceImpl.addModule-------------------");
        log.info(module.toString());
        //查看模块名字和模版是否已经存在
        Map<String, Object> map = new HashMap<>();
        map.put("name", module.getName());
        map.put("url", module.getUrl());
        List<Module> modules = moduleMapper.selectModules(map);
        if (!PublicUtility.isNullOrEmpty(modules)) {
            return new ResultBean(644);
        }
        //判断是否插入成功
        int i = moduleMapper.insert(module);
        if (i < INSERT) {
            return new ResultBean(645);
        }
        return new ResultBean(200);
    }

    @Override
    public ResultBean putModule(@RequestBody Module module) {
        log.info("----------------------进入进入ModuleServiceImpl.putModule-------------------");
        log.info(module.toString());
        //查看模块名字和模版是否已经存在
        Map<String, Object> map = new HashMap<>();
        map.put("name", module.getName());
        map.put("url", module.getUrl());
        map.put("id",module.getId());
        List<Module> modules = moduleMapper.selectModules(map);
        if (!PublicUtility.isNullOrEmpty(modules)) {
            return new ResultBean(644);
        }
        //判断更新成功
        int i = moduleMapper.updateByPrimaryKeySelective(module);
        if (i < INSERT) {
            return new ResultBean(646);
        }
        return new ResultBean(200);
    }

    @Override
    public ResultBean delModule(@RequestParam(name = "id") Long id) {
        log.info("----------------------进入进入ModuleServiceImpl.delModule-------------------");
        log.info(id.toString());
        int i = moduleMapper.deleteByPrimaryKey(id);
        if (i < DEL) {
            return new ResultBean(647);
        }
        return new ResultBean(200);
    }
}
