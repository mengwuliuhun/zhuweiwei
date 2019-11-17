package cn.kgc.ssm.mybatis;

import cn.kgc.ssm.entity.CoolCategory;
import cn.kgc.ssm.entity.CoolItem;
import cn.kgc.ssm.entity.vo.CoolCategoryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CoolCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CoolCategory record);

    int insertSelective(CoolCategory record);

    CoolCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CoolCategory record);

    int updateByPrimaryKey(CoolCategory record);
    /**
     * 通过姓名和类别模糊查询得到用户集合
     * @param name
     * @param category
     * @return
     */
    List<CoolCategory> selectCoolCategoryListLikeByNameAndCategory(@Param("name") String name, @Param("category") String category);
    /**
     * 通过类别查询得到用户集合
     * @param category
     * @return
     */
    List<CoolCategory> selectCoolCategoryListByCategory(@Param("category") String category);

    /**
     * 得到用户类别集合
     * @return
     */
    List<CoolCategoryVo>selectCategoryList();
}