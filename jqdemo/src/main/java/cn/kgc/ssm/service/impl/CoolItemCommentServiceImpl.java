package cn.kgc.ssm.service.impl;

import cn.kgc.ssm.mybatis.CoolItemCommentMapper;
import cn.kgc.ssm.service.CoolItemCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CoolItemCommentServiceImpl implements CoolItemCommentService {
    @Resource
    CoolItemCommentMapper cdo;
}
