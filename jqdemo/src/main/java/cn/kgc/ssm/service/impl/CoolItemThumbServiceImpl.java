package cn.kgc.ssm.service.impl;

import cn.kgc.ssm.entity.CoolItem;
import cn.kgc.ssm.entity.CoolItemThumb;
import cn.kgc.ssm.entity.User;
import cn.kgc.ssm.mybatis.CoolItemMapper;
import cn.kgc.ssm.mybatis.CoolItemThumbMapper;
import cn.kgc.ssm.mybatis.UserMapper;
import cn.kgc.ssm.service.CoolItemThumbService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CoolItemThumbServiceImpl implements CoolItemThumbService {
   @Resource
   CoolItemThumbMapper tdao;
   @Resource
   UserMapper mdao;
   @Resource
   CoolItemMapper cdao;

    /**
     * 点赞
     * @param thumb
     * @return
     */
    @Override
    @Transactional
    public int insertCoolIt(CoolItemThumb thumb) {
        System.out.println("CoolItemThumbServiceImpl insertCoolIt 进入  "+thumb);
        if(thumb==null)
        {
            throw new RuntimeException("请传递正确对象");
        }
        if(thumb.getUserId()==null)
        {
            throw new RuntimeException("请确保用户id存在");
        }
        if(thumb.getItemId()==null)
        {
            throw new RuntimeException("请确保产品id存在");
        }
        CoolItem coolItem=cdao.selectByPrimaryKey(thumb.getItemId());
        System.out.println(coolItem);
        if(coolItem==null)
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
        List<CoolItemThumb> list=tdao.selectByUserAndCoolItem(thumb);
        if(list.size()>0)
        {
            throw new RuntimeException("用户已经点赞,无法继续点赞");
        }
        return tdao.insertSelective(thumb);
    }

    /**
     * 取消点赞
     * @param thumb
     * @return
     */
    @Override
    @Transactional
    public int deleteCoolIt(CoolItemThumb thumb) {
        if(thumb==null)
        {
            throw new RuntimeException("请传递正确对象");
        }
        if(thumb.getUserId()==null)
        {
            throw new RuntimeException("请确保用户id存在");
        }
        if(thumb.getItemId()==null)
        {
            throw new RuntimeException("请确保产品id存在");
        }
        CoolItem coolItem=cdao.selectByPrimaryKey(thumb.getItemId());
        if(coolItem==null)
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
        System.out.println(thumb);
        List<CoolItemThumb> list=tdao.selectByUserAndCoolItem(thumb);
        int ret=0;
        if(list.size()>0)
        {
            ret=tdao.deleteByPrimaryKey(list.get(0).getId());
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
    public int selectByUserAndCoolItem(CoolItemThumb thumb) {
        if(thumb==null)
        {
            throw new RuntimeException("请传递正确对象");
        }
        if(thumb.getUserId()==null)
        {
            throw new RuntimeException("请确保用户id存在");
        }
        if(thumb.getItemId()==null)
        {
            throw new RuntimeException("请确保产品id存在");
        }
        return tdao.selectByUserAndCoolItem(thumb).size();
    }

}
