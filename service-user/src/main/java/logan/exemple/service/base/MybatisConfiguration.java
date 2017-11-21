package logan.exemple.service.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by logan on 2017/9/27.
 * qq：425018553
 * 多数据源，配置读取
 */
@Configuration
@MapperScan(basePackages = "logan.exemple.service.**.dao")
//extends MybatisAutoConfiguration
public class MybatisConfiguration {


    @Resource(name = "masterDataSource")
    private DataSource masterDataSource;
    @Resource(name = "slaveDataSource")
    private DataSource slaveDataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();

        fb.setDataSource(roundRobinDataSouceProxy());// 指定数据源(这个必须有，否则报错)
        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        fb.setTypeAliasesPackage("logan.exemple.service.**.model");// 指定基包
        fb.setMapperLocations(resolver.getResources("classpath:mapper/**/*.xml"));//
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        properties.setProperty("helperDialect","mysql");    //配置mysql数据库的方言
        PageInterceptor pageInterceptor =new PageInterceptor();
        pageInterceptor.setProperties(properties);
        fb.setPlugins(new Interceptor[]{pageInterceptor});
        return fb.getObject();

//        return super.sqlSessionFactory(roundRobinDataSouceProxy());
    }

    public AbstractRoutingDataSource roundRobinDataSouceProxy() {
        DataSourceRouting proxy = new DataSourceRouting();
        Map<Object, Object> targetDataResources = new HashMap<Object, Object>();
        targetDataResources.put(DataSourceContextHolder.DbType.MASTER, masterDataSource);
        targetDataResources.put(DataSourceContextHolder.DbType.SLAVE, slaveDataSource);
        proxy.setDefaultTargetDataSource(masterDataSource);//默认源
        proxy.setTargetDataSources(targetDataResources);
        proxy.afterPropertiesSet();
        return proxy;
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager() throws Exception {
        return new DataSourceTransactionManager(roundRobinDataSouceProxy());
    }

}
