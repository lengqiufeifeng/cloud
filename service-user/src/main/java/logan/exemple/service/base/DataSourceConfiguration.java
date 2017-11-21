package logan.exemple.service.base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;


/**
 * 加载数据库配置
 * Created by logan on 2017/9/27.
 * qq：425018553
 */
@Configuration
//@EnableTransactionManagement
public class DataSourceConfiguration {
    @Value("${jdbc.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name = "masterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "master")
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "slave")
    public DataSource slaveDataSource1(){
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

}
