package cn.kgc.ssm.mybatis;

import cn.kgc.ssm.entity.CoolItemComment;

public interface CoolItemCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CoolItemComment record);

    int insertSelective(CoolItemComment record);

    CoolItemComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CoolItemComment record);

    int updateByPrimaryKey(CoolItemComment record);
}