package logan.exemple.service.user.dao;

import logan.exemple.service.user.model.AvaRole;

public interface AvaRoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(AvaRole record);

    int insertSelective(AvaRole record);

    AvaRole selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(AvaRole record);

    int updateByPrimaryKey(AvaRole record);
}