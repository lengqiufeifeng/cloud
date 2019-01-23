package logan.exemple.service.user.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import logan.exemple.service.user.dao.SysUserMapper;
import logan.exemple.service.user.model.SysUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserService {
    @Resource
    SysUserMapper sysUserMapper;

    public int insert(SysUser record) {
        return sysUserMapper.insert(record);
    }

    public PageInfo<SysUser> getListBySysUser(SysUser record, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysUser> listUser = sysUserMapper.getListBySysUser(record);
        return new PageInfo<SysUser>(listUser);

    }
}