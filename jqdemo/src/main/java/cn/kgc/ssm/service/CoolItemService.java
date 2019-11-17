package cn.kgc.ssm.service;

import cn.kgc.ssm.entity.CoolItem;
import cn.kgc.ssm.entity.vo.CoolItemVo;

import java.util.List;

public interface CoolItemService {
    /**
     * 通过hot安定new来使coolItem按最热或者最新来降序排列
     * @param th
     * @return
     */
    List<CoolItemVo>selectCoolItemVoList(String th, Integer pro, Integer psize);
    /**
     * 通过名字和标题模糊查询得到用户分页集合
     * @param pno
     * @param psize
     * @return
     */
    List<CoolItem>selectCoolItPagesLikeByNameAndTitle(String name, String title, Integer pno, Integer psize);

    /**
     * 通过产品id得到产品
     * @param id
     * @return
     */
    CoolItem selectCoolIt(Integer id);
    /**
     * 如果coolItem有id,则修改coolItem,否则添加coolItem
     * @param item
     * @return
     */
    int updateOrInsertUser(CoolItem item);

}
