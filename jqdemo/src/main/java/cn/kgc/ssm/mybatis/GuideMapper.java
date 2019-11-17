package cn.kgc.ssm.mybatis;

import cn.kgc.ssm.entity.CoolCategory;
import cn.kgc.ssm.entity.Guide;
import cn.kgc.ssm.entity.vo.CoolItemVo;
import cn.kgc.ssm.entity.vo.GuideVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuideMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Guide record);

    int insertSelective(Guide record);

    Guide selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Guide record);

    int updateByPrimaryKey(Guide record);
    /**
     * 通过标题模糊查询得到用户集合
     * @param title
     * @return
     */
    List<Guide> selectGuideListLikeByTitle(@Param("title") String title);
    /**
     * 通过hot安定new来使coolItem按最热或者最新来降序排列
     * @param th
     * @return
     */
    List<GuideVo> selectGuideVoList(@Param("th") String th);
}