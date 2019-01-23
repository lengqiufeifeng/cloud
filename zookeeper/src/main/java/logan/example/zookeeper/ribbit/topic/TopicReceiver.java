package logan.example.zookeeper.ribbit.topic;


import com.rabbitmq.client.Channel;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author logan
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${Description}
 * @date 2018/2/2 15:59
 * @updateBy ${update_by}
 * @updateTime ${update_time}
 */
@Component
public class TopicReceiver {
    //    @RabbitListener(queues = "topic.message",containerFactory = "")
    @RabbitListener(queues = "topic.A" )
    @RabbitHandler
    public void process(Message message, Channel channel) throws Exception {
        System.err.println("Receiver topic.A:" + message);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = "topic.B" )
    @RabbitHandler
    public void process1(String hello) {
        System.err.println("Receiver topic.B:" + hello);
    }

    @RabbitListener(queues = {"topic.A", "topic.B"})
    @RabbitHandler
    public void process2(Message message, Channel channel) throws Exception {
//       String srt= (String)((HashMap)SerializationUtils.deserialize(message.getBody())).get("k");
        System.err.println("Receiver topic.A-B:" + message);

        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }
}
