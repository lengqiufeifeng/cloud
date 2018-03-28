package logan.exemple.component.test;


import logan.exemple.zookeeper.ZookeeperClientApp;
import logan.exemple.zookeeper.rabbit.FanoutSender;
import logan.exemple.zookeeper.rabbit.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.text.ParseException;

/**
 * Created by qiufeng on 2017/9/19.
 */
//这是JUnit的注解，通过这个注解让SpringJUnit4ClassRunner这个类提供Spring测试上下文。
@RunWith(SpringJUnit4ClassRunner.class)
//这是Spring Boot注解，为了进行集成测试，需要通过这个注解加载和配置Spring应用上下
@SpringBootTest(classes = ZookeeperClientApp.class)
@WebAppConfiguration

public class TestRabbit {
    @Resource
    private FanoutSender fanoutSender;
    @Resource
    private TopicSender topicSender;

    @Test
    public void saveSysLog() throws ParseException {

        fanoutSender.send();
        topicSender.send();
        topicSender.sendB();
    }
}
