package cn.kgc.ssm.service;

import cn.kgc.ssm.entity.vo.TryReportVo;

import java.util.List;

public interface TryReprotService {
    /**
     * 通过hot安定new来使TryReport按最热或者最新来降序排列
     * @param th
     * @return
     */
    List<TryReportVo> selectTryReportVoList(String th, Integer pro, Integer psize);
}
