package logan.exempletest.service.user;


import com.github.pagehelper.PageInfo;
import logan.common.base.utils.json.JsonFormatTool;
import logan.common.base.utils.json.JsonHandlerGson;
import logan.exemple.service.ServiceUserApplication;
import logan.exemple.service.user.model.SysUser;
import logan.exemple.service.user.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * Created by qiufeng on 2017/9/19.
 */
//这是JUnit的注解，通过这个注解让SpringJUnit4ClassRunner这个类提供Spring测试上下文。
@RunWith(SpringJUnit4ClassRunner.class)
//这是Spring Boot注解，为了进行集成测试，需要通过这个注解加载和配置Spring应用上下
@SpringBootTest(classes = ServiceUserApplication.class)
@WebAppConfiguration
//事物回滚
//@Transactional
public class SpringJUnitTestApplication {
    @Resource
    SysUserService sysUserService;
    @Test
//    @Rollback(true)//这里加无效，sysLogService.saveSysLog 加入注解事务
    public void saveSysUser(){
        SysUser sysUser=new SysUser();
        for(int i=0;i<100;i++) {
            sysUser.setUserId("user_" + i);
            sysUserService.insert(sysUser);
        }
    }
    @Test
//    @Rollback(true)//这里加无效，sysLogService.saveSysLog 加入注解事务
    public void getListBySysUser(){
        SysUser sysUser=new SysUser();
        sysUser.setTel("1");
        PageInfo<SysUser> ps= sysUserService.getListBySysUser(sysUser,1,10);
       System.out.println(JsonFormatTool.formatJson(JsonHandlerGson.ToJsonStr(ps)));
    }
}
