package logan.exemple.service.monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by lzr on 2017/4/15.
 * 使用JPA
 */

@SpringBootApplication
//@EnableDiscoveryClient
public class ServiceMtApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext app= SpringApplication.run(ServiceMtApplication.class, args);

    }

}
