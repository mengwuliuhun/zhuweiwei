package cn.kgc.ssm.service.impl;

import cn.kgc.ssm.mybatis.GuideCommentMapper;
import cn.kgc.ssm.service.GuideCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GuideCommentServiceImpl implements GuideCommentService {
    @Resource
    GuideCommentMapper gdao;
}
