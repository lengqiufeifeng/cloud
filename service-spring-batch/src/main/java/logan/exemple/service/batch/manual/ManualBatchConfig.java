package logan.exemple.service.batch.manual;


import com.sun.media.sound.InvalidDataException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 手动 启动批处理
 * <p>
 * PartitionStep
 */
@Configuration
public class ManualBatchConfig {
    private static final Logger logger = LoggerFactory.getLogger(ManualBatchConfig.class);
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    /**
     * 读取数据
     *
     * @return
     */
    @Bean
    @StepScope
    public ItemReader<User> manualReader(@Value("#{jobParameters['time']}" ) String time) {
        boolean f = false;
        SqlPagingQueryProviderFactoryBean spqpfb = new SqlPagingQueryProviderFactoryBean();
        spqpfb.setDataSource(dataSource);
        spqpfb.setSelectClause("user_id, user_name, alias_name, create_by, create_time" );
        spqpfb.setFromClause("user" );
//        spqpfb.setWhereClause("");

        Map<String, Order> map = new HashMap<String, Order>();
        map.put("user_id", Order.ASCENDING);
        spqpfb.setSortKeys(map);
        //使用默认实现
        JdbcPagingItemReader<User> reader = new JdbcPagingItemReader<User>();
        reader.setDataSource(dataSource);
        reader.setPageSize(2);
        reader.setCurrentItemCount(2);
        reader.setSaveState(false);
        reader.setRowMapper((ResultSet rs, int rowNum) -> {
            User user = new User();
            user.setUserId(rs.getString("user_id" ));
            user.setUserName(rs.getString("user_name" ));
            user.setAliasName(rs.getString("alias_name" ));
            user.setCreateBy(rs.getString("create_by" ));
            user.setCreateTime(rs.getDate("create_time" ));
            return user;
        });

        try {
            reader.setQueryProvider(spqpfb.getObject());

        } catch (Exception e) {
            logger.error("获取QueryProvider异常{}", e);
            throw new RuntimeException(e);//抛出运行异常 中止 job
        }

        return reader;
    }

    /**
     * 转换数据
     *
     * @return
     */
    @Bean
    public ItemProcessor manualProcessor() {

        return new ItemProcessor<User, Person>() {
            @Override
            public Person process(User item) throws Exception {
                logger.info("数据转换" + item.getUserId());
                Person person = new Person();
                person.setPersonId(item.getUserId());
                person.setFirstName(item.getUserName());
                if (StringUtils.isBlank(item.getAliasName())) {
                    throw new InvalidDataException("is null blank" );
                }
                person.setSecondName(item.getAliasName());
                person.setCreateBy(item.getCreateBy());
                person.setCreateTime(item.getCreateTime());
                return person;
            }
        };
    }

    /**
     * 数据写入
     *
     * @return
     */
    @Bean
    public JdbcBatchItemWriter<Person> manualWriter() {
        JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<Person>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());

        writer.setSql("INSERT INTO people (people_id,first_name, secoud_name, create_by,create_time) VALUES (:personId, :firstName, :secondName,'MANUAl', :createTime)" );
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public Job importPeopleJob(JobExeListener listener, Step stepUserToPeople) {

        return jobBuilderFactory.get("importPeopleJob" )
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(stepUserToPeople)
                .end()
                .build();
    }

    @Bean
    public Step stepUserToPeople(ItemReader<User> manualReader) {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(3);
        threadPoolTaskExecutor.setCorePoolSize(2);
        threadPoolTaskExecutor.setQueueCapacity(5);
        threadPoolTaskExecutor.initialize();//初始化线程池
        Step step = stepBuilderFactory.get("stepUserToPeople" )
                .listener(stepExecutionListener())
                .startLimit(100)//最多重启10次
                .<User, Person>chunk(2)
                .reader(manualReader)
                .processor(manualProcessor())
                .writer(manualWriter())

//                .faultTolerant()//容错
//                .retry(Exception.class)   // 重试
//                .noRetry(ParseException.class)
//                .retryLimit(1)           //每条记录重试一次
//                .listener(new RetryFailuireItemListener())
//                .skip(Exception.class)
//                .skipLimit(1)     //一共允许跳过1次异常
                .taskExecutor(threadPoolTaskExecutor)//
                .build();
        return step;
    }

    /**
     * step 执行监听
     *
     * @return
     */
    @Bean
    public StepExecutionListener stepExecutionListener() {
        StepExecutionListener stepExecutionListener = new StepExecutionListener() {

            @Override
            public void beforeStep(StepExecution stepExecution) {
                logger.info(stepExecution.getStepName() + "{}", "--------------beforeStep" );
            }

            @Override
            public ExitStatus afterStep(StepExecution stepExecution) {
                logger.info(stepExecution.getStepName() + "{}", "--------------afterStep" );
                //判断执行结果 用于决定执行下个 step
                String exitCode = stepExecution.getExitStatus().getExitCode();
                if (!exitCode.equals(ExitStatus.FAILED.getExitCode()) &&
                        stepExecution.getSkipCount() > 0) {
                    return new ExitStatus("COMPLETED WITH SKIPS" );
                } else {
                    return null;
                }
            }
        };
        return stepExecutionListener;
    }
}
