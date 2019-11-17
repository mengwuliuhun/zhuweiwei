package cn.kgc.ssm.service.impl;

import cn.kgc.ssm.entity.Admin;
import cn.kgc.ssm.mybatis.AdminMapper;
import cn.kgc.ssm.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {
@Resource
AdminMapper adao;

    /**
     * 在管理员没被禁言的情况下,通过名字和密码查询用户对象
     *
     * @param admin
     * @return
     */
    @Override
    public Admin selectByNameAndPassword(Admin admin) {
        return adao.selectByNameAndPassword(admin);
    }
}
