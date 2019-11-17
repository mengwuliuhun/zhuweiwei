package cn.kgc.ssm.mybatis;

import cn.kgc.ssm.entity.Admin;
import cn.kgc.ssm.entity.User;

import java.util.List;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
    /**
     *在管理员没被禁言的情况下,通过名字和密码查询用户对象
     * @param admin
     * @return
     */
    Admin selectByNameAndPassword(Admin admin);
}