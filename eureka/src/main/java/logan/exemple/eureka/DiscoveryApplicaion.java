package logan.exemple.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * Created by lzr on 2017/4/15.
 */

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryApplicaion extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        SimpleHostRoutingFilter
        return application.sources(DiscoveryApplicaion.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryApplicaion.class, args);
    }
}
