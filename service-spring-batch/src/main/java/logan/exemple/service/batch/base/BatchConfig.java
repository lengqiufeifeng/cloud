package logan.exemple.service.batch.base;


import logan.exemple.service.batch.manual.JobExeListener;
import logan.exemple.service.batch.manual.Person;
import logan.exemple.service.batch.manual.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 创建batch 相关bean
 */
@Deprecated
@Configuration
public class BatchConfig {
    private static final Logger logger = LoggerFactory.getLogger(BatchConfig.class);
//    @Autowired
//    DataSourceTransactionManager transactionManager;
//    @Autowired
//    public DataSource dataSource;
//
//
//    @Bean
//    public JobLauncher jobLauncher() {
//        JobLauncher jobLauncher=new SimpleJobLauncher();
//        MapJobRepositoryFactoryBean
//        return jobLauncher;
//    }


}
