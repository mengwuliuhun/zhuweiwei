package cn.kgc.ssm.service.impl;

import cn.kgc.ssm.mybatis.TryApplyMapper;
import cn.kgc.ssm.service.TryApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TryApplyServiceImpl implements TryApplyService {
    @Resource
    TryApplyMapper tdao;
}
