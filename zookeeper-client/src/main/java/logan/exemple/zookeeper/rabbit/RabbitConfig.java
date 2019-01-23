package logan.exemple.zookeeper.rabbit;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


/**
 * 服务端使用
 *
 * @author logan
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${Description}
 * @date 2018/2/2 15:50
 * @updateBy ${update_by}
 * @updateTime ${update_time}
 */
@Configuration
public class RabbitConfig {
    @Autowired
    private ConnectionFactory connectionFactory;

    //    @Bean(name="firstRabbitTemplate")
//    @Primary
//    public RabbitTemplate firstRabbitTemplate(
//            @Qualifier("firstConnectionFactory") ConnectionFactory connectionFactory
//    ){
//        RabbitTemplate firstRabbitTemplate = new RabbitTemplate(connectionFactory);
//        return firstRabbitTemplate;
//    }
    @Bean
    /** 因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置 */
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplateNew() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        return template;
    }
}
