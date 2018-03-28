package logan.exemple.zookeeper.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author logan
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${Description}
 * @date 2018/2/27 15:05
 * @updateBy ${update_by}
 * @updateTime ${update_time}
 */
@Component
public class FanoutSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        String context = "hi, fanout msg ";
        rabbitTemplate.setConfirmCallback((CorrelationData correlationData, boolean ack, String cause) -> {
            System.out.println("fanout callbakck correlationData id: " + correlationData.getId());
            System.out.println("callbakck ack: " + ack);
            System.out.println("callbakck cause: " + cause);
        });
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("fanout callbakck correlationData id: " + correlationData.getId());
        this.rabbitTemplate.convertAndSend("fanoutExchange", "topic.1", context, correlationData);
    }
}
