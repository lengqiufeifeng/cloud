package logan.example.zookeeper.controller;

import logan.example.zookeeper.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author logan
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: 服务提供端
 * @date 2018/2/9 14:23
 * @updateBy ${update_by}
 * @updateTime ${update_time}
 */
@RestController
@RequestMapping("/user" )
public class UserController {


    @RequestMapping("/getUserById/{id}" )
    public Object getUserByIdUrl(@PathVariable int id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    @RequestMapping("/getUserByIdOrUserName" )
    public Object getUserByIdOrUserName(@RequestParam(value = "id", required = false) int id, @RequestHeader("User-Agent" ) String userAgent) {
        User user = new User();
        user.setId(id);
        user.setName(userAgent);
        return user;
    }

    @RequestMapping("/getUserById" )
    public Object getUserById(int id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    @RequestMapping("/getUserByUser" )
    public Object getUserByUser(@RequestBody User usr) {

        return usr;
    }
}
