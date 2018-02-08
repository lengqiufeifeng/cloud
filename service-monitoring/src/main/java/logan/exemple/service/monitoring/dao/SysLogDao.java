package logan.exemple.service.monitoring.dao;

import logan.exemple.service.monitoring.model.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by qiufeng on 2017/9/19.
 */
//@Repository
public interface SysLogDao extends JpaRepository<SysLog, Long> ,JpaSpecificationExecutor<SysLog> {
    SysLog findBylogId(Integer logId);

    /**
     * 默认是只读事务，添加 @Modifying
     * @param id
     * @return
     */
    @Modifying
    @Query("update SysLog as s_log set s_log.logType = 0 where s_log.logId = ?1")
    int modifyByLogId(int id);

}
