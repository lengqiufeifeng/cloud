package logan.exemple.service.base;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by logan on 2017/9/27.
 * qq：425018553
 */
@Aspect
@Component
@Order(0)  // execute before @Transactional
public class DataSourceAspect {
    /**
     * 使用空方法定义切点表达式 user
     */
    @Pointcut("execution(* logan.exemple.service.user.dao..*(..))" )
    public void declareJointPointUser() {
    }

    /**
     * 使用定义切点表达式的方法进行切点表达式的引入 user
     */
    @Before("declareJointPointUser()" )
    public void setDataSourceKey(JoinPoint point) {
        DataSourceContextHolder.setDbType(DataSourceContextHolder.DbType.MASTER);

    }

    /**
     * 使用空方法定义切点表达式 logs
     */
    @Pointcut("execution(* logan.exemple.service.monitoring.dao..*(..))" )
    public void declareJointPointMonitoring() {
    }

    /**
     * 使用定义切点表达式的方法进行切点表达式的引入 user
     */
    @Before("declareJointPointMonitoring()" )
    public void setDataSourceKeyMonitoring(JoinPoint point) {
        DataSourceContextHolder.setDbType(DataSourceContextHolder.DbType.SLAVE);
    }
}