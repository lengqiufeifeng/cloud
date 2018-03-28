package logan.exemple.zookeeper.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author logan
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${Description}
 * @date 2018/2/27 14:50
 * @updateBy ${update_by}
 * @updateTime ${update_time}
 */
@Component
public class TopicSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void setConfirmCallback(){
        rabbitTemplate.setConfirmCallback((CorrelationData correlationData, boolean ack, String cause) -> {
            System.out.println("Topic callbakck correlationData id: " + correlationData.getId());
            System.out.println("callbakck ack: " + ack);
            System.out.println("callbakck cause: " + cause);
        });
    }
    public void send() {

        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("Topic correlationData id: " + correlationData.getId());
        String context = "hi, i am message all";
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.message", context, correlationData);
    }



    public void sendB() {
        HashMap<String,Object> hashMap=new HashMap<String,Object>();
        String context = "hi, i am messages B";
        hashMap.put("k",context);

        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("Topic correlationData id: " + correlationData.getId());
        this.rabbitTemplate.convertAndSend("topicExchange", "topic", hashMap, correlationData);
    }

}
