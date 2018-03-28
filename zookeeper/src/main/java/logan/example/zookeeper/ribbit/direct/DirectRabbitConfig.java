package logan.example.zookeeper.ribbit.direct;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 完全匹配 and amq.direct
 * @author logan
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${Description}
 * @date 2018/2/27 14:40
 * @updateBy ${update_by}
 * @updateTime ${update_time}
 */
@Configuration
public class DirectRabbitConfig {
    final static String a = "topic.A";

    @Bean
    public Queue queueMessage() {
        return new Queue(DirectRabbitConfig.a);
    }


    @Bean
    DirectExchange exchange() {
        return new DirectExchange("topicExchange");
    }

    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

}
