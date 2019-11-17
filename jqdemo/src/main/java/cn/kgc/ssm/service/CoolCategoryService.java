package cn.kgc.ssm.service;

import cn.kgc.ssm.entity.CoolCategory;
import cn.kgc.ssm.entity.vo.CoolCategoryVo;

import java.util.List;

public interface CoolCategoryService {
    /**
     * 通过产品类别id得到产品类别
     * @param id
     * @return
     */
    CoolCategory selectCoolCategory(Integer id);

    /**
     * 通过名字和标题模糊查询得到用户分页集合
     * @param name
     * @param category
     * @param pno
     * @param psize
     * @return
     */
    List<CoolCategory> selectCoolCaPagesLikeByNameAndCategory(String name, String category, Integer pno, Integer psize);
    /**
     * 如果coolItem有id,则修改coolItem,否则添加coolItem
     * @param coolCategory
     * @return
     */
    int updateOrInsertCoolCa(CoolCategory coolCategory);

    /**
     * 得到产品种类vo集合
     * @return
     */
    List<CoolCategoryVo> selectCoolCategoryVoList();
}
