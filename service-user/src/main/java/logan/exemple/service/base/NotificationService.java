package logan.exemple.service.base;

import logan.exemple.service.common.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by qiufeng on 2017/9/22.
 * 通知服务
 */
@Component
public class NotificationService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 发送通知
     */
    public void sendNtc(Notification notification){
        //TODO 待优化

    }


}
