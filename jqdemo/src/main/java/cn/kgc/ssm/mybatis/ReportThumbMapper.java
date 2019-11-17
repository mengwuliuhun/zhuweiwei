package cn.kgc.ssm.mybatis;

import cn.kgc.ssm.entity.GuideThumb;
import cn.kgc.ssm.entity.ReportThumb;

import java.util.List;

public interface ReportThumbMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReportThumb record);

    int insertSelective(ReportThumb record);

    ReportThumb selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReportThumb record);

    int updateByPrimaryKey(ReportThumb record);
    /**
     * 通过用户id和报告id得到ReportThumb集合
     * @param thumb
     */
    List<ReportThumb> selectByUserAndReport(ReportThumb thumb);
}