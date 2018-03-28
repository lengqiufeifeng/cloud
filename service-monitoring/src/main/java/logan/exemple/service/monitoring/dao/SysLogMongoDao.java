package logan.exemple.service.monitoring.dao;

import logan.exemple.service.monitoring.model.SysLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;


/**
 * Created by qiufeng on 2017/9/19.
 */
public interface SysLogMongoDao extends MongoRepository<SysLog, String> {

    /**
     *
     * 注意注解要空格分隔
     * @param sysCode
     * @param serviceCode
     * @param createTime =< 结果
     * @return
     */
    @Query(value="{ 'sysCode' : ?0 , 'serviceCode' : ?1 , 'createTime' : { '$gte' : ?2 } }",fields = "{ '_id' : 0 }")
    List<SysLog> findBySysCode(String sysCode, String serviceCode, Date createTime);

}
