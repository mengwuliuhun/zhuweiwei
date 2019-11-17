package cn.kgc.ssm.service;

import cn.kgc.ssm.entity.vo.TryItemVo;

import java.util.List;

public interface TryItemService {
    /**
     * 通过类别和类型来确定TryItemVo视图集合,其中类别为apply
     * try,end分别对应在试用之前,中,以及试用介绍
     * @param category
     * @param type
     * @return
     */
    List<TryItemVo>selectTryItemsByCategoryOrType(String category, String type, Integer pno, Integer psize);
}
