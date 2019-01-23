package logan.example.zookeeper.ribbit.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 规则匹配
 *
 * @author logan
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${Description}
 * @date 2018/2/27 14:40
 * @updateBy ${update_by}
 * @updateTime ${update_time}
 */
@Configuration
public class TopicRabbitConfig {
    final static String a = "topic.A";
    final static String b = "topic.B";

    @Bean
    public Queue queueMessage() {
        return new Queue(TopicRabbitConfig.a, true);
    }

    @Bean
    public Queue queueMessages() {
        return new Queue(TopicRabbitConfig.b, true);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("topicExchange", true
                , false);
    }

    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.*" );
    }
//*(星号)代表任意一个单词
//#(hash)0个或多个单词

    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#" );
    }
}
