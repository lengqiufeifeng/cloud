package logan.example.zookeeper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author logan
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${Description}
 * @date 2018/2/9 14:23
 * @updateBy ${update_by}
 * @updateTime ${update_time}
 */
@RestController
@RequestMapping("/zk")
public class ZookeeperController {
    @Autowired
    private LoadBalancerClient loadBalancer;

    @Autowired
    private DiscoveryClient discovery;

    @RequestMapping("/getAllServices")
    public Object all() {
        return discovery.getServices();
    }

    @RequestMapping("/discoveryServices")
    public Object discovery(String serviceId) {
        return loadBalancer.choose(serviceId);

    }
}
