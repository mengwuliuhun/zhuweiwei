package cn.kgc.ssm.service.impl;

import cn.kgc.ssm.mybatis.ReportCommentMapper;
import cn.kgc.ssm.service.ReportCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ReportCommentServiceImpl implements ReportCommentService {
    @Resource
    ReportCommentMapper rdao;
}
