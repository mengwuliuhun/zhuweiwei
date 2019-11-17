package cn.kgc.ssm.service.impl;

import cn.kgc.ssm.entity.Guide;
import cn.kgc.ssm.entity.User;
import cn.kgc.ssm.entity.vo.GuideVo;
import cn.kgc.ssm.mybatis.GuideMapper;
import cn.kgc.ssm.service.GuideService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class GuideServiceImpl implements GuideService {
    @Resource
    GuideMapper gdao;


    /**
     * 通过导购id得到导购
     *
     * @param id
     * @return
     */
    @Override
    public Guide selectGuide(Integer id) {
        return gdao.selectByPrimaryKey(id);
    }

    /**
     * 通过标题模糊查询得到导购分页集合
     *
     * @param title
     * @param pno
     * @param psize
     * @return
     */
    @Override
    public List<Guide> selectGuidePagesLikeByTitle(String title, Integer pno, Integer psize) {
        if(title!=null)
        {
            if("".equals(title.trim()))
            {
                title=null;
            }
        }
        PageHelper.startPage(pno,psize);
        List<Guide>list=gdao.selectGuideListLikeByTitle(title);
        return list;
    }

    /**
     * 如果Guide有id,则修改Guide,否则添加Guide
     *
     * @param guide
     * @return
     */
    @Override
    public int updateOrInsertGuide(Guide guide) {
        int ret=0;
        List<User>list=new ArrayList<>();
        if(guide.getId()==null)
        {

            ret=gdao.insertSelective(guide);
        }else {
            ret=gdao.updateByPrimaryKeySelective(guide);
        }
        return ret;
    }
    @Override
    public List<GuideVo> selectGuideVoList(String th, Integer pro, Integer psize) {
        PageHelper.startPage(pro,psize);
        return gdao.selectGuideVoList(th);
    }
}
