package cn.kgc.ssm.mybatis;

import cn.kgc.ssm.entity.GuideComment;

public interface GuideCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GuideComment record);

    int insertSelective(GuideComment record);

    GuideComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GuideComment record);

    int updateByPrimaryKey(GuideComment record);
}