package logan.exemple.service.batch;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.FilterRegistration;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzr on 2017/4/15.
 *
 *
 *
 */
@EnableBatchProcessing
@EnableScheduling
@SpringBootApplication
//@EnableDiscoveryClient
public class ServiceBatchApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext app= SpringApplication.run(ServiceBatchApplication.class, args);

}


}
