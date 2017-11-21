package logan.exemple.service.user.dao;

import logan.exemple.service.user.model.AvaResource;

public interface AvaResourceMapper {
    int deleteByPrimaryKey(String resourceId);

    int insert(AvaResource record);

    int insertSelective(AvaResource record);

    AvaResource selectByPrimaryKey(String resourceId);

    int updateByPrimaryKeySelective(AvaResource record);

    int updateByPrimaryKey(AvaResource record);
}