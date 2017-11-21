package logan.exemple.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lzr on 2017/4/15.
 */

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@EnableDiscoveryClient
@RestController
public class ServiceUserApplication extends SpringBootServletInitializer {
    @GetMapping("/logan/exemple/service")
    public String service() {
        return "this service is user management";
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ServiceUserApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApplication.class, args);
    }
}
