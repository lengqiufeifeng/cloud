package logan.exemple.service.monitoring.service;

import logan.exemple.service.monitoring.dao.SysLogDao;
import logan.exemple.service.monitoring.model.SysLog;
import logan.exemple.service.monitoring.vo.SysLogVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiufeng on 2017/9/19.
 */
@Service
public class SysLogService {

    @Autowired
    SysLogDao sysLogDao;

    @Transactional
    public SysLog saveSysLog(SysLog sysLog) {
        return sysLogDao.save(sysLog);
    }

    public SysLog findBySyslogId(Integer logId) {
        return sysLogDao.findBylogId(logId);
    }

    /**
     * 根据属性查询
     *
     * @param sysLog
     * @param size
     * @param page
     * @return
     */
    public Page<SysLog> findBySysLogVo(SysLogVo sysLog, int size, int page) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<SysLog> lt = null;
        lt = sysLogDao.findAll(new Specification<SysLog>() {
            @Override
            public Predicate toPredicate(Root<SysLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                if (StringUtils.isNotEmpty(sysLog.sysName)) {
                    list.add(cb.equal(root.get("sysName").as(String.class), sysLog.sysName));
                }
                if (StringUtils.isNotEmpty(sysLog.sysCode)) {
                    list.add(cb.equal(root.get("sysCode").as(String.class), sysLog.sysCode));
                }
                if (StringUtils.isNotEmpty(sysLog.serviceCode)) {
                    list.add(cb.equal(root.get("serviceCode").as(String.class), sysLog.serviceCode));
                }
                if (StringUtils.isNotEmpty(sysLog.serviceName)) {
                    list.add(cb.equal(root.get("serviceName").as(String.class), sysLog.serviceName));
                }
                if (StringUtils.isNotEmpty(sysLog.methodName)) {
                    list.add(cb.equal(root.get("methodName").as(String.class), sysLog.methodName));
                }
                if (StringUtils.isNotEmpty(sysLog.requestData)) {
                    list.add(cb.like(root.get("requestData").as(String.class), "%" + sysLog.requestData + "%"));
                }
                if (StringUtils.isNotEmpty(sysLog.responseData)) {
                    list.add(cb.like(root.get("responseData").as(String.class), "%" + sysLog.responseData + "%"));
                }
                if (null != sysLog.logType) {
                    list.add(cb.equal(root.get("logType").as(Integer.class), sysLog.logType));
                }
                list.add(cb.between(root.get("createTime"),sysLog.beginDate,sysLog.endDate));
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        }, pageable);

        return lt;
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
            lt = sysLogDao.findAll(pageable);

        } else {
            //创建匹配器，即如何使用查询条件
            ExampleMatcher matcher = ExampleMatcher.matching();
            matcher.withIgnorePaths("createTime", "updataTime");
            Example<SysLog> example = Example.of(sysLog, matcher);
            lt = sysLogDao.findAll(example, pageable);
        }

        return lt;
    }
}
