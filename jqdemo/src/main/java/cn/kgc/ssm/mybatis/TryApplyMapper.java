package cn.kgc.ssm.mybatis;

import cn.kgc.ssm.entity.TryApply;

public interface TryApplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TryApply record);

    int insertSelective(TryApply record);

    TryApply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TryApply record);

    int updateByPrimaryKey(TryApply record);
}