package logan.exemple.zookeeper.service;

import logan.exemple.zookeeper.service.impl.ServiceInfoFailureImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author logan
 * @Title: ServiceInfo
 * @ProjectName cloud
 * @Description: TODO
 * @date 2018/9/3013:32
 */
@FeignClient(name = "service-zookeeper", fallback = ServiceInfoFailureImpl.class)
public interface ServiceInfo {
    @RequestMapping("/zk/getAllServices" )
    public Object all();


}
