package com.academy.admin.controller;

import com.academy.core.dto.PageBean;
import com.academy.core.dto.Result;
import com.academy.core.dto.ResultBean;
import com.academy.core.pojo.Manager;
import com.academy.core.service.ManagerService;
import com.academy.core.util.AccessTokenUtil;
import com.academy.core.util.BPwdEncoderUtils;
import com.academy.core.util.PageUtil;
import com.academy.core.util.PublicUtility;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.academy.core.constant.Constant.*;


@RestController
@Slf4j
@RequestMapping("a")
@Api(tags = "后台Manager模块接口")
public class ManageController {
    @Autowired
    private ManagerService managerService;

    /**
     * ------------------------------后台manager接口:拦截/a/a/*路由---------------------------
     */
    @ApiOperation(value = "登录", notes = "登录成功后返回Access_token,Access_token放在请求头")
    @PostMapping("/login")
    public ResultBean login(@RequestParam("name") String name,
                        @RequestParam("pwd") String pwd) {
        return managerService.getManagerInfo(name, pwd);

    }


    @ApiOperation(value = "注销登录", notes = "点击注销即退出登录")
    @PostMapping("/logout")
    public ResultBean logout() {
        return new ResultBean(200);
    }


    @ApiOperation(value = "账户详情", notes = "根据URL获取id查看账户的详细信息")
    @GetMapping("/a/manager/{id}")
    public ResultBean getManager(@PathVariable(name = "id") Long id) {
        log.info("----------------------进入进入ManageController.getManagers-------------------");
        log.info("id:" + id);
        return managerService.getManageById(id);
    }


    @ApiOperation(value = "管理员列表", notes = "根据条件的不同返回满足条件的管理列表。默认查询的是全部数据，默认：page=1，size=10")
    @GetMapping("/a/manager/search")
    public ResultBean getManagers(@RequestParam(name = "name", required = false) String name,
                                  @RequestParam(name = "roleId", required = false) Long roleId,
                                  @RequestParam(name = "page", required = false) Integer page,
                                  @RequestParam(name = "size", required = false) Integer size) {
        log.info("--------------进入ManageController.getManagers--------------");
        if (page == null || page < ONE) {
            page = START_PAGE;
        }
        if (size == null || size < ONE) {
            size = START_SIZE;
        }
        log.info("name:" + name + ",roleId:" + roleId + ",page" + page + ",size:" + size);
        int start = PageUtil.getStart(page, size);
        ResultBean resultBean = managerService.getManagers(name, roleId, start, size);
        if (!PublicUtility.isNullOrEmpty(resultBean.getData())) {
            log.info("加入当前页数据：" + page);
            ((PageBean) resultBean).setPageNum(page);
        }
        return resultBean;
    }


    @ApiOperation(value = "新增管理员", notes = "创建一个账号:密码，姓名，角色id不能为null，默认：statu=using")
    @PostMapping("/a/manager")
    public ResultBean addManager(HttpServletRequest request, @RequestBody Manager manager) {
        log.info("--------------进入ManageController.addManager--------------");
        //参数校验
        if (PublicUtility.isNullOrEmpty(manager)) {
            return new ResultBean(203);
        }
        if (PublicUtility.strIsEmpty(manager.getPwd())) {
            return new ResultBean(626);
        }
        if (PublicUtility.strIsEmpty(manager.getName())) {
            return new ResultBean(627);
        }
        if (PublicUtility.numIsEmpty(manager.getRoleId())) {
            return new ResultBean(628);
        }
        if (PublicUtility.strIsEmpty(manager.getStatus())) {
            manager.setStatus(STATUS);
        }
        log.info("manager:" + manager);
        //从请求头中获取登录的id
        Long managerId = Long.valueOf(AccessTokenUtil.getAccessTokeValues(request, MANAGER_ID).toString());
        //加入时间和部分信息
        manager.setPwd(BPwdEncoderUtils.BCryptPassword(manager.getPwd()));
        manager.setCreateBy(managerId);
        manager.setUpdateBy(managerId);
        manager.setCreateAt(System.currentTimeMillis());
        manager.setUpdateAt(System.currentTimeMillis());

        return managerService.addManager(manager);
    }


    @ApiOperation(value = "修改管理员", notes = "根据URL获取管理员的id进行资料的修改")
    @PutMapping("/a/manager/{id}")
    public ResultBean putManager(HttpServletRequest request, @PathVariable(name = "id") Long id,
                                 @RequestBody Manager manager) {
        log.info("--------------进入ManageController.putManager--------------");
        if (PublicUtility.isNullOrEmpty(manager)) {
            return new ResultBean(625);
        }
        Long managerId = Long.valueOf(AccessTokenUtil.getAccessTokeValues(request, MANAGER_ID).toString());
        manager.setUpdateBy(managerId);
        manager.setId(id);
        manager.setUpdateAt(System.currentTimeMillis());
        log.info("ManageController.putManager:manager:" + manager);
        return managerService.putManager(manager);
    }


    @ApiOperation(value = "删除管理员", notes = "根据URL获取管理员的id进行删除")
    @DeleteMapping("/a/manager/{id}")
    public ResultBean delManager(@PathVariable(name = "id") Long id) {
        log.info("--------------进入ManageController.delManager--------------");
        return managerService.delManager(id);
    }


    @ApiOperation(value = "修改密码", notes = "输入新密码和旧密码进行修改，新密码和旧密码不能相同")
    @PutMapping("/a/pwd")
    public ResultBean putPwd(HttpServletRequest request,
                             @RequestParam(name = "newPwd") String newPwd,
                             @RequestParam(name = "oldPwd") String oldPwd) {
        if (PublicUtility.strIsEmpty(newPwd) || PublicUtility.strIsEmpty(oldPwd)) {
            return new ResultBean(630);
        }
        if (newPwd.equals(oldPwd)) {
            return new ResultBean(621);
        }
        //创建manager对象作为载体
        Manager manager = new Manager();
        //取出managerId作为更新人
        Long managerId = Long.valueOf(AccessTokenUtil.getAccessTokeValues(request, MANAGER_ID).toString());
        manager.setId(managerId);
        manager.setUpdateBy(managerId);
        manager.setUpdateAt(System.currentTimeMillis());
        manager.setPwd(BPwdEncoderUtils.BCryptPassword(newPwd));

        return new ResultBean(200);
    }

}
