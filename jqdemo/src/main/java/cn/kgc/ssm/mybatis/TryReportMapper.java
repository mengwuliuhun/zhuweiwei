package cn.kgc.ssm.mybatis;

import cn.kgc.ssm.entity.TryReport;
import cn.kgc.ssm.entity.vo.GuideVo;
import cn.kgc.ssm.entity.vo.TryReportVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TryReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TryReport record);

    int insertSelective(TryReport record);

    TryReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TryReport record);

    int updateByPrimaryKey(TryReport record);
    /**
     * 通过hot和new来使coolItem按最热或者最新来降序排列
     * @param th
     * @return
     */
    List<TryReportVo> selectTryReportVoList(@Param("th") String th);
}