package logan.exemple.service.user.dao;

import logan.exemple.service.user.model.SysUser;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByAccount(String account);

    List<SysUser> getListBySysUser(SysUser record);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}