package cn.kgc.ssm.service;

import cn.kgc.ssm.entity.GuideThumb;

public interface GuideThumbService {
    /**
     * 点赞
     * @param thumb
     * @return
     */
    public int insertGuideThumb(GuideThumb thumb);

    /**
     * 取消点赞
     * @param thumb
     * @return
     */
    int deleteGuideThumb(GuideThumb thumb);

    /**
     * 通过
     * @param thumb
     * @return
     */
    int selectByUserAndGuide(GuideThumb thumb);

    /**
     * 删除或者添加点赞
     * @param thumb
     * @return
     */
    int changeByUserAndGuide(GuideThumb thumb);
}
