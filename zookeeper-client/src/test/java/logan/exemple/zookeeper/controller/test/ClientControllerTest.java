package logan.exemple.zookeeper.controller.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import logan.exemple.zookeeper.ZookeeperClientApp;
import logan.exemple.zookeeper.vo.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * @author logan
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${Description}
 * @date 2018/2/9 17:30
 * @updateBy ${update_by}
 * @updateTime ${update_time}
 */
//这是JUnit的注解，通过这个注解让SpringJUnit4ClassRunner这个类提供Spring测试上下文。
@RunWith(SpringJUnit4ClassRunner.class)
//这是Spring Boot注解，为了进行集成测试，需要通过这个注解加载和配置Spring应用上下
@SpringBootTest(classes = ZookeeperClientApp.class)
@WebAppConfiguration
@EnableDiscoveryClient
public class ClientControllerTest {

    private static final Logger LOG = LoggerFactory.getLogger(ClientControllerTest.class);

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    @Autowired
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;
    ObjectMapper mapper = null;

    @Before
    public void setupMockMvc() throws Exception {
        DispatcherServlet dispatcherServlet = (DispatcherServlet) context.getBean("dispatcherServlet");
//        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        mapper = mappingJackson2HttpMessageConverter.getObjectMapper();

    }

    /***
     *
     *
     * @throws Exception
     */
    @Test
    public void getUserByIdUrl() throws Exception {

        //调用接口，传入添加的用户参数
        try {
            ResultActions result = mockMvc.perform(
                    get("/client/getUserById/123")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            );
            //判断返回值，是否达到预期，
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.resultCode", Matchers.isIn(new Integer[]{0, 1})));
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)
            //使用jsonPath解析返回值，判断具体的内容
//                    .andExpect(jsonPath("$.resultCode", is(1)))
//                    .andExpect(jsonPath("$.errCode", notNullValue()))
//                .andExpect(jsonPath("$.log.id", not(0)))
//                .andExpect(jsonPath("$.log.name", is("testuser2")));
            String s = result.andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
            System.err.println("返回结果：" + s);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
    }

    /***
     *
     *
     * @throws Exception
     */
    @Test
    public void getUserByIdOrUserName() throws Exception {

        //调用接口，传入添加的用户参数
        try {
            ResultActions result = mockMvc.perform(
                    post("/client/getUserByIdOrUserName")
                            .header("User-Agent", "mockMvc")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                            .param("id", "123466")
            );
            String s = result.andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
            System.err.println("返回结果：" + s);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    /***
     *
     *
     * @throws Exception
     */
    @Test
    public void getUserById() throws Exception {

        //调用接口，传入添加的用户参数
        try {
            ResultActions result = mockMvc.perform(
                    get("/client/getUserById")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                            .param("id", "123466")
            );
            String s = result.andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
            System.err.println("返回结果：" + s);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    /***
     *
     *
     * @throws Exception
     */
    @Test
    public void getUserByUser() throws Exception {
        User user = new User();
        user.setId(123456);
        user.setName("zk");
        //调用接口，传入添加的用户参数
        try {
            ResultActions result = mockMvc.perform(
                    post("/client/getUserByUser")
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .content(mapper.writeValueAsString(user))
            );
            String s = result.andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
            System.err.println("返回结果：" + s);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
