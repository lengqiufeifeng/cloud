package logan.exemple.service.batch.manual;

/**
 * @author logan
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/1/8 16:40
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 * job 执行监听
 */
@Component
public class JobExeListener extends JobExecutionListenerSupport {

    private static final Logger logger = LoggerFactory.getLogger(JobExeListener.class);

    public JobExeListener() {
        super();
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        logger.info(jobExecution.getJobInstance().getJobName() + "{}", "--------------afterJob" );
        super.afterJob(jobExecution);
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info(jobExecution.getJobInstance().getJobName() + "{}", "--------------beforeJob" );
        super.beforeJob(jobExecution);
    }
}
