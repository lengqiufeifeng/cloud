package logan.exemple.service.monitoring.service;

import logan.exemple.service.monitoring.dao.SysLogMongoDao;
import logan.exemple.service.monitoring.model.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by qiufeng on 2017/9/19.
 */
@Service
public class SysLogMongoService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    SysLogMongoDao sysLogMongoDao;


    public SysLog saveSysLog(SysLog sysLog) {
        return sysLogMongoDao.save(sysLog);
    }
   public List<SysLog> findBySysCode(String sysCode,String serviceCode,Date createTime){
      return sysLogMongoDao.findBySysCode(sysCode,serviceCode,createTime);
   }

    /**
     * 使用 mongoTemplate
     * @param sysLog
     * @return
     */
    public SysLog findOne(SysLog sysLog) {
        Criteria criteria = Criteria.where("sysCode")
                .is(sysLog.sysCode)
                .and("createTime")
                .gte(sysLog.createTime);
        Query query = new Query(criteria);

        return mongoTemplate.findOne(query, SysLog.class);
    }
    /**
     * 根据属性查询，不处理时间段
     *
     * @param sysLog
     * @param size
     * @param page
     * @return
     */
    public Page<SysLog> findSysLogs(SysLog sysLog, int size, int page) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<SysLog> lt = null;
        if (null == sysLog) {
            lt = sysLogMongoDao.findAll(pageable);

        } else {
            //创建匹配器，即如何使用查询条件
            ExampleMatcher matcher = ExampleMatcher.matching();
            matcher.withIgnorePaths("createTime", "updateTime");
            Example<SysLog> example = Example.of(sysLog, matcher);
            lt = sysLogMongoDao.findAll(example, pageable);
        }

        return lt;
    }
}
