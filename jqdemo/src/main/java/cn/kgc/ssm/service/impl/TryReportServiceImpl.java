package cn.kgc.ssm.service.impl;

import cn.kgc.ssm.entity.TryApply;
import cn.kgc.ssm.entity.vo.TryReportVo;
import cn.kgc.ssm.mybatis.TryApplyMapper;
import cn.kgc.ssm.mybatis.TryItemMapper;
import cn.kgc.ssm.mybatis.TryReportMapper;
import cn.kgc.ssm.mybatis.UserMapper;
import cn.kgc.ssm.service.TryReprotService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TryReportServiceImpl implements TryReprotService {
    @Resource
    TryReportMapper tdao;
    @Resource
    UserMapper udao;
    @Resource
    TryApplyMapper tadao;
    @Resource
    TryItemMapper tidao;

    /**
     * 通过hot安定new来使TryReport按最热或者最新来降序排列
     * @param th
     * @param pro
     * @param psize
     * @return
     */
    @Override
    @Transactional
    public List<TryReportVo> selectTryReportVoList(String th, Integer pro, Integer psize) {
        PageHelper.startPage(pro,psize);
        List<TryReportVo> list=tdao.selectTryReportVoList(th);
        for (TryReportVo tryReportVo : list) {
            String id=tryReportVo.getUserId();
            tryReportVo.setUser(udao.selectByPrimaryKey(Integer.valueOf(id)));
            Integer applyId=tryReportVo.getApplyId();
            TryApply tryApply=tadao.selectByPrimaryKey(applyId);
            tryReportVo.setVo(tidao.selectTryItemVoById(tryApply.getItemId()));
        }
        return list;
    }
}
