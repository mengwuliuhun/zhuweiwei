package cn.kgc.ssm.service.impl;

import cn.kgc.ssm.entity.CoolCategory;
import cn.kgc.ssm.entity.User;
import cn.kgc.ssm.entity.vo.CoolCategoryVo;
import cn.kgc.ssm.mybatis.CoolCategoryMapper;
import cn.kgc.ssm.service.CoolCategoryService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoolCategoryServiceImpl implements CoolCategoryService {
    @Resource
    CoolCategoryMapper cdao;

    /**
     * 通过产品类别id得到产品类别
     *
     * @param id
     * @return
     */
    @Override
    public CoolCategory selectCoolCategory(Integer id) {
        return cdao.selectByPrimaryKey(id);
    }

    /**
     * 通过名字和标题模糊查询得到用户分页集合
     *
     * @param name
     * @param category
     * @param pno
     * @param psize
     * @return
     */
    @Override
    public List<CoolCategory> selectCoolCaPagesLikeByNameAndCategory(String name, String category, Integer pno, Integer psize) {
        if(name!=null)
        {
            if("".equals(name.trim()))
            {
                name=null;
            }
        }
        if(category!=null)
        {
            if("".equals(category.trim()))
            {
                category=null;
            }
        }
        PageHelper.startPage(pno,psize);
        List<CoolCategory>list=cdao.selectCoolCategoryListLikeByNameAndCategory(name,category);
        return list;
    }
    /**
     * 如果coolItem有id,则修改coolItem,否则添加coolItem
     *
     * @param coolCategory
     * @return
     */
    @Override
    @Transactional
    public int updateOrInsertCoolCa(CoolCategory coolCategory) {
        int ret=0;
        List<User>list=new ArrayList<>();
        System.out.println(coolCategory);
        if(coolCategory.getId()==null)
        {

            ret=cdao.insertSelective(coolCategory);
        }else {
            ret=cdao.updateByPrimaryKeySelective(coolCategory);
        }
        System.out.println("CoolCategoryServiceImpl updateOrInsertCoolCa   "+ret);
        return ret;
    }

    /**
     * 得到产品种类vo集合
     *
     * @return
     */
    @Override
    @Transactional
    public List<CoolCategoryVo> selectCoolCategoryVoList() {
        List<CoolCategoryVo>list=cdao.selectCategoryList();
        for (CoolCategoryVo coolCategoryVo : list) {
            coolCategoryVo.setCoolCategories(cdao.selectCoolCategoryListByCategory(coolCategoryVo.getCategory()));
        }
        return list;
    }
}
