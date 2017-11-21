package logan.exemple.service.user.dao;

import logan.exemple.service.user.model.AvaRoleResource;

public interface AvaRoleResourceMapper {
    int deleteByPrimaryKey(String roleResourceId);

    int insert(AvaRoleResource record);

    int insertSelective(AvaRoleResource record);

    AvaRoleResource selectByPrimaryKey(String roleResourceId);

    int updateByPrimaryKeySelective(AvaRoleResource record);

    int updateByPrimaryKey(AvaRoleResource record);
}