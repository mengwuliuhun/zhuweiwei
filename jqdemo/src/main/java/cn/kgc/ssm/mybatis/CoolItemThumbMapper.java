package cn.kgc.ssm.mybatis;

import cn.kgc.ssm.entity.CoolItemThumb;

import java.util.List;

public interface CoolItemThumbMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CoolItemThumb record);

    int insertSelective(CoolItemThumb record);

    CoolItemThumb selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CoolItemThumb record);

    int updateByPrimaryKey(CoolItemThumb record);

    /**
     * 通过用户id和产品id得到CoolItemThumb集合
     * @param thumb
     */
    List<CoolItemThumb>selectByUserAndCoolItem(CoolItemThumb thumb);
}