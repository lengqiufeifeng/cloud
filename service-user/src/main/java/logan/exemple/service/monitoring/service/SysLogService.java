package logan.exemple.service.monitoring.service;

import logan.exemple.service.monitoring.dao.SysLogMapper;
import logan.exemple.service.monitoring.model.SysLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysLogService {
    @Resource
    SysLogMapper sysLogMapper;

    public int insert(SysLog record) {
        return sysLogMapper.insert(record);
    }

}