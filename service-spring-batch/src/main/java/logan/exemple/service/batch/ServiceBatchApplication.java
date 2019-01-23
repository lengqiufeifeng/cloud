package logan.exemple.service.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by lzr on 2017/4/15.
 */
@EnableBatchProcessing
@EnableScheduling
@SpringBootApplication
//@EnableDiscoveryClient
public class ServiceBatchApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(ServiceBatchApplication.class, args);

    }


}
