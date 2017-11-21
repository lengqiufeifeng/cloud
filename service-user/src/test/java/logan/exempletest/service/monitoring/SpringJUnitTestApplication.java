package logan.exempletest.service.monitoring;

import logan.exemple.service.ServiceUserApplication;
import logan.exemple.service.monitoring.model.SysLog;
import logan.exemple.service.monitoring.service.SysLogService;
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
    SysLogService sysLogService;
    @Test
    public void saveSysLogs(){
        SysLog saveSysLog=new SysLog();
        saveSysLog.setSysCode("user");
        int result  = sysLogService.insert(saveSysLog);

    }
}
