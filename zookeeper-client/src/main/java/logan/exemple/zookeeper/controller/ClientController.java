package logan.exemple.zookeeper.controller;


import logan.exemple.zookeeper.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author logan
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: 服务调用端
 * @date 2018/2/9 14:23
 * @updateBy ${update_by}
 * @updateTime ${update_time}
 */
@RestController
@RequestMapping("/client")
@RibbonClient(name = "service-zookeeper")
public class ClientController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/getUserById/{id}")
    public Object getUserByIdUrl(@PathVariable int id) {
        return restTemplate.getForEntity("http://service-zookeeper/user/getUserById/{1}", String.class, id);
    }

    @RequestMapping("/getUserByIdOrUserName")
    public Object getUserByIdOrUserName(@RequestParam(value = "id", required = false) int id, @RequestHeader("User-Agent") String userAgent) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("User-Agent",userAgent);
        //键值对必须是字符类型
        MultiValueMap<String, Object> map= new LinkedMultiValueMap<String, Object>();
        map.add("id", String.valueOf(id));
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(map, headers);
        return restTemplate.postForEntity("http://service-zookeeper/user/getUserByIdOrUserName", request, User.class);

    }

    @RequestMapping("/getUserById")
    public Object getUserById(int id) {
        return restTemplate.postForEntity("http://service-zookeeper/user/getUserById?id={1}", null, String.class, id);

    }

    @RequestMapping("/getUserByUser")
    public Object getUserByUser(@RequestBody User usr) {
        return restTemplate.postForEntity("http://service-zookeeper/user/getUserByUser", usr, User.class);
    }
}
