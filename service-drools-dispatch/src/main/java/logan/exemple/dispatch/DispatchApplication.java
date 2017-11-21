package logan.exemple.dispatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 继承SpringBootServletInitializer，打包war
 *  * Created by lzr on 2017/4/15.
 */
@SpringBootApplication
public class DispatchApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DispatchApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(DispatchApplication.class, args);
    }
}
