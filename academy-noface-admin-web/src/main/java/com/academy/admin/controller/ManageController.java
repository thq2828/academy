package com.academy.admin.controller;

import com.academy.core.dto.ResultBean;
import com.academy.core.pojo.JWT;
import com.academy.core.pojo.Manager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@RestController
@Slf4j
@RequestMapping("a")
@Api(tags = "后台Manager模块接口")
public class ManageController {
    private static Manager manager;
    private static JWT jwt;
    /**
     * 初始化manner假数据
     */
    static {
        manager = new Manager();
        manager.setId(1L);
        manager.setName("admin");
        manager.setPwd("123456");
        manager.setRoleId(1L);
        manager.setStatus("using");
        manager.setCreateAt(System.currentTimeMillis());
        manager.setUpdateAt(System.currentTimeMillis());
        manager.setCreateBy(1L);
        manager.setUpdateBy(1L);
    }
    static {
        jwt=new JWT();
        jwt.setAccess_token("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NDcwOTI2NjgsInVzZXJfbmFtZSI6ImhhaXFpbmciLCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIl0sImp0aSI6IjYzMmM0NTljLWFiM2EtNGIwNi05ZGIyLTBmZDNjMmI0ZTI0OCIsImNsaWVudF9pZCI6InVzZXItc2VydmljZSIsInNjb3BlIjpbInNlcnZpY2UiXX0.SGSxO2ikib_wbo5z8s-mnFqNbqrj_Ij5JvNgHIog2jo2-8V7rQonfMJkJqZEaQjn6KQ0RjvEQEHQESJqoINdd84H78ojgChehJWAbUq4LPulH63Kp50EJbv4GLU7XI5LFxuDY0xc85oH2oVLlYBE07gauGLDR_copW0EpqLKXchvZ0Pem21CNeGnTxstn1aZVIw23wgpoK5wRMGCdYq0Xs0QDczKZ9840WB10251EDnFnut6t_EzMQ0LHYBwgy53mSd-wIcsU0w_nKVKTvYpv5X-e9Yml9Sb3KQgs65pHyCu_osumbBL7jbM7XULwipb6RHaHeMjkd-jepPaFFf0xg");
        jwt.setToken_type("bearer");
        jwt.setExpires_in(86399);
        jwt.setRefresh_token("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJoYWlxaW5nIiwic2NvcGUiOlsic2VydmljZSJdLCJhdGkiOiI2MzJjNDU5Yy1hYjNhLTRiMDYtOWRiMi0wZmQzYzJiNGUyNDgiLCJleHAiOjE1NDk1OTgyNjgsImF1dGhvcml0aWVzIjpbIlJPTEVfQURNSU4iXSwianRpIjoiNzUzNzE4OTYtZmMzNy00MTc4LWIyMTktOTc4NDQzNzQxYTM1IiwiY2xpZW50X2lkIjoidXNlci1zZXJ2aWNlIn0.ZVWdyTrTBX1ZBrNQIa315N34ZHovDqMcWxU1FTArq-mZuDLeaO4brFg7OomjWuMDLZZGNxDak3bHhb5Y23aqysgVJsonTFKVm-vizWVzMHL9c4A6nj_OMrplLdqpWTMX-yq-lTNj0yreisOtM_33opvIkUK4xDkksu5h2eiAqKg3Gm6aMYKhaOP2JyRlKBWzd9MNPZpLaiPdUlkJA_BrMjOWvIK8wm8bPf3ZJD-EXhz0NaMdqQaQxSlDOCf2DSNfkk1wMFGdBlp4W-p-n28x8FEmL2lun2TQ6F3M348-FmD87qMvwoedGbuCd03Can2HeK6Rzn09YLe30ZFE1KR3Cg");
        jwt.setScope("service");
        jwt.setJti("632c459c-ab3a-4b06-9db2-0fd3c2b4e248");
    }

    /**
     * ------------------------------后台manager接口:拦截/a/a/*路由---------------------------
     */
    @ApiOperation(value = "登录",notes = "登录成功后返回角色id")
    @PostMapping("/login")
    public ResultBean login(@RequestParam("name")String name,
                            @RequestParam("pwd")String pwd){

        return new ResultBean<JWT>(200,jwt);
    }


    @ApiOperation(value = "注销登录",notes = "点击注销即退出登录")
    @PostMapping("/logout")
    public ResultBean logout(){
        return new ResultBean(200);
    }

    @ApiOperation(value = "账户详情",notes = "根据URL获取id查看账户的详细信息")
    @GetMapping("/a/manager/{id}")
    public ResultBean getManager(@PathVariable(name = "id")Long id){
        return new ResultBean<Manager>(200,manager);
    }

    @ApiOperation(value = "管理员列表",notes = "根据条件的不同返回满足条件的管理列表")
    @GetMapping("/a/manager/search")
    public ResultBean getManagers(@RequestParam(name = "name",required = false)String name,
                                  @RequestParam(name="roleId",required = false)Long roleId,
                                  @RequestParam(name="page",required = false)Integer page,
                                  @RequestParam(name = "size",required = false)Integer size){
        if (page==null||size<1){
            page=1;
        }
        if (size==null||size<1){
            size=10;
        }
        List<Manager> managers=new ArrayList<>();
        for (int i=0;i<size;i++){
            managers.add(manager);
        }
        return new ResultBean<List<Manager>>(200,managers);
    }

    @ApiOperation(value = "新增管理员",notes = "创建一个账号")
    @PostMapping("/a/manager")
    public ResultBean addManager(@RequestBody Manager manager){
        return new ResultBean(200);
    }

    @ApiOperation(value = "修改管理员",notes = "根据URL获取管理员的id进行资料的修改")
    @PutMapping("/a/manager/{id}")
    public ResultBean putManager(@PathVariable(name = "id")Long id,
                                 @RequestBody Manager manager){
        return new ResultBean(200);
    }

    @ApiOperation(value = "删除管理员",notes = "根据URL获取管理员的id进行删除")
    @DeleteMapping("/a/manager/{id}")
    public ResultBean delManager(@PathVariable(name = "id")Long id){
        return new ResultBean(200,"删除成功");
    }

    @ApiOperation(value = "修改密码",notes = "输入新密码和旧密码进行修改")
    @PutMapping("/a/pwd")
    public ResultBean putPwd(@RequestParam(name = "newPwd")String newPwd,
                             @RequestParam(name = "oldPwd")String oldPwd){
        if(newPwd.equals(oldPwd)){
            return new ResultBean(621);
        }
        return new ResultBean(200);

    }

}
