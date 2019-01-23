package logan.exemple.service.batch.base;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.List;

/**
 * 内嵌 batch admin 配置
 * 无效
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class BatchWebConfig implements ServletContextInitializer {
    /**
     * 配置请求视图映射
     *
     * @return
     */
//    @Bean
//    public InternalResourceViewResolver resourceViewResolver() {
//        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
//        //请求视图文件的前缀地址
//        internalResourceViewResolver.setPrefix("/WEB-INF/views/");
//        //请求视图文件的后缀
//        internalResourceViewResolver.setSuffix(".jsp");
//        return internalResourceViewResolver;
//    }

    /**
     * 添加过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean setFilterRegistration() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        ShallowEtagHeaderFilter sehFilter = new ShallowEtagHeaderFilter();
        filterRegistrationBean.setFilter(sehFilter);
        filterRegistrationBean.addUrlPatterns("/*" );
        return filterRegistrationBean;
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //设置 context-param
        servletContext.setInitParameter("contextConfigLocation", "classpath*:/org/springframework/batch/admin/web/resources/webapp-config.xml" );
    }
}
