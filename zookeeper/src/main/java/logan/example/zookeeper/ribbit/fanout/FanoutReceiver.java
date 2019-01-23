package logan.example.zookeeper.ribbit.fanout;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author logan
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${Description}
 * @date 2018/2/27 15:11
 * @updateBy ${update_by}
 * @updateTime ${update_time}
 */
@Component
public class FanoutReceiver {
    @RabbitHandler
    @RabbitListener(queues = "fanout.A" )
    public void processa(String message) {
        System.out.println("fanout Receiver A  : " + message);
    }

    @RabbitHandler
    @RabbitListener(queues = "fanout.B" )
    public void processb(Message message, Channel channel) throws Exception {
        System.out.println("fanout Receiver B  : " + message);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitHandler
    @RabbitListener(queues = "fanout.C" )
    public void processc(Message message, Channel channel) throws Exception {
        System.out.println("fanout Receiver C  : " + message);
        channel.basicNack(message.getMessageProperties().getDeliveryTag(), true, false);
    }
}
