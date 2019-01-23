package logan.exemple.test.service;

import logan.common.base.boot.utils.json.JsonHandlerGson;
import logan.exemple.service.monitoring.ServiceMtApplication;
import logan.exemple.service.monitoring.model.SysLog;
import logan.exemple.service.monitoring.service.SysLogMongoService;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by qiufeng on 2017/9/19.
 */
//这是JUnit的注解，通过这个注解让SpringJUnit4ClassRunner这个类提供Spring测试上下文。
@RunWith(SpringJUnit4ClassRunner.class)
//这是Spring Boot注解，为了进行集成测试，需要通过这个注解加载和配置Spring应用上下
@SpringBootTest(classes = ServiceMtApplication.class)
@WebAppConfiguration

public class TestSysLogMongoDB {
    @Resource
    SysLogMongoService sysLogMongoService;

    @Test
//    @Rollback(true)//这里加无效，sysLogService.saveSysLog 加入注解事务
    public void saveSysLog() throws ParseException {
        SysLog sysLog = new SysLog();

        sysLog.sysName = "单元测试";
        sysLog.sysCode = "logan/exemple/test";
        sysLog.serviceName = "单元测试";
        sysLog.serviceCode = "logan/exemple/test";
        sysLog.methodName = "addLog";
        sysLog.logType = 1;
        sysLog.requestData = "来自单元测试日志";
        sysLog.responseData = "来自日志记录反馈";
        sysLog.createTime = new Date();
        sysLog.updateTime = new Date();
        SysLog result = sysLogMongoService.saveSysLog(sysLog);
        System.out.println("---insert:" + JsonHandlerGson.ToJsonStr(result));


        SysLog sysLogf = new SysLog();
        sysLogf.sysCode = "logan/exemple/test";
        String[] s = new String[]{"yyyy-MM-dd hh:mm:ss"};
        sysLogf.serviceCode = "logan/exemple/test";

        Page<SysLog> oag = sysLogMongoService.findSysLogs(sysLogf, 2, 1);
        System.err.println("---get:" + JsonHandlerGson.ToJsonStr(oag.getContent()));
        sysLogf.createTime = DateUtils.parseDate("2018-02-01 00:00:00", s);

        SysLog sysLogOne = sysLogMongoService.findOne(sysLogf);
        System.err.println("---get:" + JsonHandlerGson.ToJsonStr(sysLogOne));

        List<SysLog> result1 = sysLogMongoService.findBySysCode(sysLogf.sysCode, sysLogf.serviceCode, sysLogf.createTime);

        System.err.println("---get:" + JsonHandlerGson.ToJsonStr(result1));


    }
}
