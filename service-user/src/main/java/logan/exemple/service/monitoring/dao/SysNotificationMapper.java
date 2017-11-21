package logan.exemple.service.monitoring.dao;

import logan.exemple.service.monitoring.model.SysNotification;

public interface SysNotificationMapper {
    int deleteByPrimaryKey(Integer ntId);

    int insert(SysNotification record);

    int insertSelective(SysNotification record);

    SysNotification selectByPrimaryKey(Integer ntId);

    int updateByPrimaryKeySelective(SysNotification record);

    int updateByPrimaryKey(SysNotification record);
}