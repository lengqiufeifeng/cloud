package logan.examples.zookeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
//@EnableDiscoveryClient
public class ZookeeperApp
{

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperApp.class, args);
        System.err.println("--------------------");
        System.err.println("--------------------");
//        SpringApplicationBuilder springApplicationBuilder=   new SpringApplicationBuilder(ZookeeperApp.class);
//        springApplicationBuilder.web(WebApplicationType.SERVLET).run(args);

    }
}
