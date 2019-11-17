package cn.kgc.ssm.service;

import cn.kgc.ssm.entity.Guide;
import cn.kgc.ssm.entity.vo.GuideVo;

import java.util.List;

public interface GuideService {
    /**
     * 通过导购id得到导购
     * @param id
     * @return
     */
    Guide selectGuide(Integer id);

    /**
     * 通过标题模糊查询得到导购分页集合
     * @param title
     * @param pno
     * @param psize
     * @return
     */
    List<Guide> selectGuidePagesLikeByTitle(String title, Integer pno, Integer psize);
    /**
     * 如果Guide有id,则修改Guide,否则添加Guide
     * @param guide
     * @return
     */
    int updateOrInsertGuide(Guide guide);
    /**
     * 通过hot安定new来使coolItem按最热或者最新来降序排列
     * @param th
     * @return
     */
    List<GuideVo>selectGuideVoList(String th, Integer pro, Integer psize);
}
