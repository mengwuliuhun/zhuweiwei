package cn.kgc.ssm.service.impl;

import cn.kgc.ssm.entity.vo.TryItemVo;
import cn.kgc.ssm.mybatis.TryItemMapper;
import cn.kgc.ssm.service.TryItemService;
import cn.kgc.ssm.util.DateUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class TryItemServiceImpl implements TryItemService {
    @Resource
    TryItemMapper tdao;

    /**
     * 通过类别和类型来确定TryItemVo视图集合,其中类别为apply
     * try,end分别对应在试用之前,中,以及试用介绍
     *
     * @param category
     * @param type
     * @return
     */
    @Override
    @Transactional
    public List<TryItemVo> selectTryItemsByCategoryOrType(String category, String type, Integer pno, Integer psize) {
        PageHelper.startPage(pno,psize);
        List<TryItemVo> list= tdao.selectTryItemsByCategoryOrType(category,type);
        Date now= DateUtils.getNow();
        for (TryItemVo vo : list) {
            if(vo.getAcnt()==null){
                vo.setAcnt(0);
            }
            if(vo.getRcnt()==null){
                vo.setRcnt(0);
            }
            if(now.before(vo.getBeginDate())){
                vo.setState("apply");
                vo.setRemainDays(DateUtils.daysBetween(now,vo.getBeginDate()));
            }
            else if(now.after(vo.getEndDate())){
                vo.setState("end");
            }
            else{
                vo.setState("try");
            }
        }
        return list;
    }
}
