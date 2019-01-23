package logan.exemple.test.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import logan.common.base.boot.utils.json.JsonFormatTool;
import logan.exemple.service.monitoring.ServiceMtApplication;
import logan.exemple.service.monitoring.model.SysLog;
import logan.exemple.service.monitoring.vo.SysLogVo;
import org.apache.commons.lang3.time.DateUtils;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by qiufeng on 2017/9/19.
 */
//这是JUnit的注解，通过这个注解让SpringJUnit4ClassRunner这个类提供Spring测试上下文。
@RunWith(SpringJUnit4ClassRunner.class)
//这是Spring Boot注解，为了进行集成测试，需要通过这个注解加载和配置Spring应用上下
@SpringBootTest(classes = ServiceMtApplication.class)
@WebAppConfiguration
//@Transactional
public class SpringJUnitTestApplication {
    private static final Logger LOG = LoggerFactory.getLogger(SpringJUnitTestApplication.class);

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    @Autowired
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;
    ObjectMapper mapper = null;

    @Before
    public void setupMockMvc() throws Exception {
        DispatcherServlet dispatcherServlet = (DispatcherServlet) context.getBean("dispatcherServlet" );
//        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        mapper = mappingJackson2HttpMessageConverter.getObjectMapper();

    }

    /***
     * 测试添加用户接口
     *
     * @throws Exception
     */
    @Test
    public void testaddLog() throws Exception {
        class SysLogs extends SysLog {
            public String baseN = "你好！";
        }

        //构造添加的用户信息
        SysLogs sysLog = new SysLogs();
        sysLog.sysName = "单元测试";
        sysLog.sysCode = "logan/exemple/test";
        sysLog.serviceName = "单元测试";
        sysLog.serviceCode = "logan/exemple/test";
        sysLog.methodName = "addLog";
        sysLog.logType = 1;
        sysLog.requestData = "来自单元测试日志";
        sysLog.responseData = "来自日志记录反馈";
        sysLog.createTime = new Date();
        sysLog.updateTime = new Date();

//        try {
//            MockHttpServletRequestBuilder mhsrb = MockMvcRequestBuilders.post("/log/addlog");
//            mhsrb.param("data", "data");
//            //添加请求参数
//            MvcResult result = mockMvc.perform(mhsrb)
//                    .andDo(MockMvcResultHandlers.print()).andReturn();
//            String s = result.getResponse().getContentAsString();
//            System.err.println("返回结果：" + s);
//        } catch (Exception e) {
//            LOG.error(e.getMessage());
//        }
        //调用接口，传入添加的用户参数
        try {
            ResultActions result = mockMvc.perform(post("/log/addlogp" )
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(mapper.writeValueAsString(sysLog)))
                    //判断返回值，是否达到预期，
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.resultCode", Matchers.isIn(new Integer[]{0, 1})));
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)
            //使用jsonPath解析返回值，判断具体的内容
//                    .andExpect(jsonPath("$.resultCode", is(1)))
//                    .andExpect(jsonPath("$.errCode", notNullValue()))
//                .andExpect(jsonPath("$.log.id", not(0)))
//                .andExpect(jsonPath("$.log.name", is("testuser2")));
            String s = result.andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
            System.err.println("返回结果：" + s);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Test
    public void getLogs() throws Exception {
        //构造添加的用户信息
        SysLogVo sysLog = new SysLogVo();
        sysLog.sysCode = "logan/exemple/test";
        sysLog.beginDate = DateUtils.parseDate("2017-09-20 00:34:57", "yyyy-MM-dd HH:mm:ss" );
        sysLog.endDate = DateUtils.parseDate("2017-09-21 00:34:57", "yyyy-MM-dd HH:mm:ss" );
        try {
            ResultActions result = mockMvc.perform(post("/log/getlogs/2/2" )
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(mapper.writeValueAsString(sysLog)))
                    //判断返回值，是否达到预期，
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.resultCode", Matchers.isIn(new Integer[]{0, 1})));
            String s = result.andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
            System.err.println("返回结果：\n" + JsonFormatTool.formatJson(s));
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
