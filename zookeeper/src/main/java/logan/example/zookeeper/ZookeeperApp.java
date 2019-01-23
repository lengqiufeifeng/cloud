package logan.example.zookeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


/**
 * @author logan
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${Description}
 * @date 2018/2/5 18:04
 * @updateBy ${update_by}
 * @updateTime ${update_time}
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ZookeeperApp {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Do any additional configuration here
        return builder.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperApp.class, args);
        System.err.println("--------------------" );

//        SpringApplicationBuilder springApplicationBuilder=   new SpringApplicationBuilder(ZookeeperApp.class);
//        springApplicationBuilder.web(WebApplicationType.SERVLET).run(args);
//        System.err.println("--------------------");

    }
}
