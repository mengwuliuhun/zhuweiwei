package cn.kgc.ssm.mybatis;

import cn.kgc.ssm.entity.CoolItemThumb;
import cn.kgc.ssm.entity.GuideThumb;

import java.util.List;

public interface GuideThumbMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GuideThumb record);

    int insertSelective(GuideThumb record);

    GuideThumb selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GuideThumb record);

    int updateByPrimaryKey(GuideThumb record);
    /**
     * 通过用户id和导购id得到GuideThumb集合
     * @param thumb
     */
    List<GuideThumb> selectByUserAndGuide(GuideThumb thumb);
}