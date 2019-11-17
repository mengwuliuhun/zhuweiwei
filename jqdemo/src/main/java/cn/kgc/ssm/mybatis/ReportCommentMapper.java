package cn.kgc.ssm.mybatis;

import cn.kgc.ssm.entity.ReportComment;

public interface ReportCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReportComment record);

    int insertSelective(ReportComment record);

    ReportComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReportComment record);

    int updateByPrimaryKey(ReportComment record);
}