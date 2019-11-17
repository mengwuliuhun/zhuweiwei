package cn.kgc.ssm.service;

import cn.kgc.ssm.entity.CoolItemThumb;

public interface CoolItemThumbService {
    /**
     * 点赞
     * @param thumb
     * @return
     */
    public int insertCoolIt(CoolItemThumb thumb);

    /**
     * 取消点赞
     * @param thumb
     * @return
     */
   int deleteCoolIt(CoolItemThumb thumb);

    /**
     * 通过
     * @param thumb
     * @return
     */
    int selectByUserAndCoolItem(CoolItemThumb thumb);
}
