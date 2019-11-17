package cn.kgc.ssm.mybatis;

import cn.kgc.ssm.entity.CoolItem;
import cn.kgc.ssm.entity.User;
import cn.kgc.ssm.entity.vo.CoolItemVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CoolItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CoolItem record);

    int insertSelective(CoolItem record);

    CoolItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CoolItem record);

    int updateByPrimaryKey(CoolItem record);

    /**
     * 通过hot安定new来使coolItem按最热或者最新来降序排列
     * @param th
     * @return
     */
    List<CoolItemVo> selectCoolItemVoList(@Param("th") String th);
    /**
     * 通过姓名和电话模糊查询得到产品集合
     * @param name
     * @param title
     * @return
     */
    List<CoolItem>selectCoolItemListLikeByNameAndTitle(@Param("name") String name, @Param("title") String title);

}