package cn.kgc.ssm.service.impl;

import cn.kgc.ssm.entity.Guide;
import cn.kgc.ssm.entity.GuideThumb;
import cn.kgc.ssm.entity.User;
import cn.kgc.ssm.mybatis.GuideMapper;
import cn.kgc.ssm.mybatis.GuideThumbMapper;
import cn.kgc.ssm.mybatis.UserMapper;
import cn.kgc.ssm.service.GuideThumbService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GuideThumbServiceImpl implements GuideThumbService {
    @Resource
    GuideThumbMapper gtdao;
    @Resource
    UserMapper mdao;
    @Resource
    GuideMapper gdao;

    /**
     * 点赞
     * @param thumb
     * @return
     */
    @Override
    @Transactional
    public int insertGuideThumb(GuideThumb thumb) {
        System.out.println("CoolItemThumbServiceImpl insertCoolIt 进入  "+thumb);
        if(thumb==null)
        {
            throw new RuntimeException("请传递正确对象");
        }
        if(thumb.getUserId()==null)
        {
            throw new RuntimeException("请确保用户id存在");
        }
        if(thumb.getGuideId()==null)
        {
            throw new RuntimeException("请确保导购id存在");
        }
        Guide guide=gdao.selectByPrimaryKey(thumb.getGuideId());
        if(guide==null)
        {
            throw new RuntimeException("没有对应id的产品,无法点赞");
        }
        User user=mdao.selectByPrimaryKey(thumb.getUserId());
        if(user==null)
        {
            throw new RuntimeException("没有对应id的用户,无法点赞");
        }
        if(user.getState()<=0)
        {
            throw new RuntimeException(" 用户没有权限,无法点赞");
        }
        List<GuideThumb> list=gtdao.selectByUserAndGuide(thumb);
        if(list.size()>0)
        {
            throw new RuntimeException("用户已经点赞,无法继续点赞");
        }
        return gtdao.insertSelective(thumb);
    }

    /**
     * 取消点赞
     * @param thumb
     * @return
     */
    @Override
    @Transactional
    public int deleteGuideThumb(GuideThumb thumb) {
        if(thumb==null)
        {
            throw new RuntimeException("请传递正确对象");
        }
        if(thumb.getUserId()==null)
        {
            throw new RuntimeException("请确保用户id存在");
        }
        if(thumb.getGuideId()==null)
        {
            throw new RuntimeException("请确保导购id存在");
        }
        Guide guide=gdao.selectByPrimaryKey(thumb.getGuideId());
        if(guide==null)
        {
            throw new RuntimeException(" 没有对应id的产品,无法点赞");
        }
        User user=mdao.selectByPrimaryKey(thumb.getUserId());
        if(user==null)
        {
            throw new RuntimeException(" 没有对应id的用户,无法点赞");
        }
        if(user.getState()<=0)
        {
            throw new RuntimeException(" 用户没有权限,无法取消点赞");
        }
        List<GuideThumb> list=gtdao.selectByUserAndGuide(thumb);
        int ret=0;
        if(list.size()>0)
        {
            ret=gtdao.deleteByPrimaryKey(list.get(0).getId());
        }else {
            throw new RuntimeException(" 用户尚未点赞,无法取消点赞");
        }
        return ret;
    }

    /**
     * 通过
     * @param thumb
     * @return
     */
    @Override
    public int selectByUserAndGuide(GuideThumb thumb) {
        if(thumb==null)
        {
            throw new RuntimeException("请传递正确对象");
        }
        if(thumb.getUserId()==null)
        {
            throw new RuntimeException("请确保用户id存在");
        }
        if(thumb.getGuideId()==null)
        {
            throw new RuntimeException("请确保产品id存在");
        }
        return gtdao.selectByUserAndGuide(thumb).size();
    }

    /**
     * 删除或者添加点赞
     *
     * @param thumb
     * @return
     */
    @Override
    @Transactional
    public int changeByUserAndGuide(GuideThumb thumb) {
        int ret = selectByUserAndGuide(thumb);
        if (ret > 0) {
            ret = deleteGuideThumb(thumb);
            if (ret > 0) {
                return -1;
            } else {
                throw new RuntimeException("取消点赞失败");
            }
        } else {
            ret = insertGuideThumb(thumb);
            if (ret > 0) {
                return 1;
            } else {
                throw new RuntimeException("点赞失败");
            }
        }
    }
}
