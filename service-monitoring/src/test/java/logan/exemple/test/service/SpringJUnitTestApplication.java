package logan.exemple.test.service;

import logan.exemple.service.monitoring.ServiceMtApplication;
import logan.exemple.service.monitoring.model.SysLog;
import logan.exemple.service.monitoring.service.SysLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by qiufeng on 2017/9/19.
 */
//这是JUnit的注解，通过这个注解让SpringJUnit4ClassRunner这个类提供Spring测试上下文。
@RunWith(SpringJUnit4ClassRunner.class)
//这是Spring Boot注解，为了进行集成测试，需要通过这个注解加载和配置Spring应用上下
@SpringBootTest(classes = ServiceMtApplication.class)
@WebAppConfiguration
//事物回滚
@Transactional
public class SpringJUnitTestApplication {
    @Resource
    SysLogService sysLogService;

    @Test
//    @Rollback(true)//这里加无效，sysLogService.saveSysLog 加入注解事务
    public void saveSysLog(){
        SysLog saveSysLog=new SysLog();
        saveSysLog.methodName="";
        SysLog result  = sysLogService.saveSysLog(saveSysLog);
        System.out.println("insert:"+result.logId);
        SysLog result1  = sysLogService.findBySyslogId(result.logId);
        System.out.println("get:"+result1.logId);
    }
}
