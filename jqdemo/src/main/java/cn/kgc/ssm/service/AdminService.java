package cn.kgc.ssm.service;

import cn.kgc.ssm.entity.Admin;

public interface AdminService {
    /**
     * 在管理员没被禁言的情况下,通过名字和密码查询用户对象
     * @param admin
     * @return
     */
    Admin selectByNameAndPassword(Admin admin);
}
