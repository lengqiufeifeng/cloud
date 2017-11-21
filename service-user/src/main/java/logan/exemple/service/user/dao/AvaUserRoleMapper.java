package logan.exemple.service.user.dao;

import logan.exemple.service.user.model.AvaUserRole;

public interface AvaUserRoleMapper {
    int deleteByPrimaryKey(String userRoleId);

    int insert(AvaUserRole record);

    int insertSelective(AvaUserRole record);

    AvaUserRole selectByPrimaryKey(String userRoleId);

    int updateByPrimaryKeySelective(AvaUserRole record);

    int updateByPrimaryKey(AvaUserRole record);
}