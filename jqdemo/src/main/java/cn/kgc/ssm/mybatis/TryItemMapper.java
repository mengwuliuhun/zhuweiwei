package cn.kgc.ssm.mybatis;

import cn.kgc.ssm.entity.TryItem;
import cn.kgc.ssm.entity.vo.TryItemVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TryItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TryItem record);

    int insertSelective(TryItem record);

    TryItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TryItem record);

    int updateByPrimaryKey(TryItem record);

    /**
     * 通过类别和类型来确定TryItemVo视图集合,其中类别为apply
     * try,end分别对应在试用之前,中,以及试用介绍
     * @param category
     * @param type
     * @return
     */
    List<TryItemVo> selectTryItemsByCategoryOrType(@Param("category") String category, @Param("type") String type);
    /**
     * 通过id来确定TryItemVo视图集合
     * @param id
     * @return
     */
    TryItemVo selectTryItemVoById(@Param("id") Integer id);
}