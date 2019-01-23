package logan.exemple.service.batch.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author logan
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${Description}
 * @date 2018/1/10 13:06
 * @updateBy ${update_by}
 * @updateTime ${update_time}
 */
@Component
public class ExampleTask {
    private final static Logger logger = LoggerFactory.getLogger(ExampleTask.class);
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job importPeopleJob;

    @Scheduled(cron = "0/10 * * * * ?" )
    public void taskRun() {
        logger.info("开始执行定时任务" );
        JobParametersBuilder jobPara = new JobParametersBuilder();  //设置参数
        jobPara.addString("time", "2018-01-15 00:00:00" );
        jobPara.addString("max", "9" );
        try {
            jobLauncher.run(importPeopleJob, jobPara.toJobParameters());
        } catch (Exception e) {
            logger.error("task 执行错误：{}", e);
//            e.printStackTrace();
        }
    }
}
