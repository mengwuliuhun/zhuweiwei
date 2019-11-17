package cn.kgc.ssm.service.impl;

import cn.kgc.ssm.entity.CoolItem;
import cn.kgc.ssm.entity.User;
import cn.kgc.ssm.entity.vo.CoolItemVo;
import cn.kgc.ssm.mybatis.CoolItemMapper;
import cn.kgc.ssm.service.CoolItemService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoolItemServiceImpl implements CoolItemService {
    @Resource
    CoolItemMapper cdao;

    @Override
    public List<CoolItemVo> selectCoolItemVoList(String th, Integer pro, Integer psize) {
        System.out.println("CoolItemServiceImpl selectCoolItemVoList"+psize+pro);
        PageHelper.startPage(pro,psize);
        return cdao.selectCoolItemVoList(th);
    }

    /**
     * 通过名字和标题模糊查询得到用户分页集合
     *
     * @param name
     * @param title
     * @param pno
     * @param psize
     * @return
     */
    @Override
    public List<CoolItem> selectCoolItPagesLikeByNameAndTitle(String name, String title, Integer pno, Integer psize) {
        if(name!=null)
        {
            if("".equals(name.trim()))
            {
                name=null;
            }
        }
        if(title!=null)
        {
            if("".equals(title.trim()))
            {
                title=null;
            }
        }
        PageHelper.startPage(pno,psize);
        List<CoolItem>list=cdao.selectCoolItemListLikeByNameAndTitle(name,title);
        return list;
    }

    /**
     * 通过产品id得到产品
     *
     * @param id
     * @return
     */
    @Override
    public CoolItem selectCoolIt(Integer id) {
        return cdao.selectByPrimaryKey(id);
    }

    /**
     * 如果coolItem有id,则修改coolItem,否则添加coolItem
     *
     * @param item
     * @return
     */
    @Override
    public int updateOrInsertUser(CoolItem item) {
        int ret=0;
        List<User>list=new ArrayList<>();
        if(item.getId()==null)
        {

            ret=cdao.insertSelective(item);
        }else {
            ret=cdao.updateByPrimaryKeySelective(item);
        }
        return ret;
    }
}
