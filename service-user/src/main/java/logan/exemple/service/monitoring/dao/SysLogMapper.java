package logan.exemple.service.monitoring.dao;

import logan.exemple.service.monitoring.model.SysLog;

public interface SysLogMapper {
    int deleteByPrimaryKey(Integer logId);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(Integer logId);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);
}