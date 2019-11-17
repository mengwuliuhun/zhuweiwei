package cn.kgc.ssm.Controller;

import cn.kgc.ssm.entity.CoolCategory;
import cn.kgc.ssm.entity.vo.CoolCategoryVo;
import cn.kgc.ssm.service.CoolCategoryService;
import cn.kgc.ssm.util.JSONData;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CoolCategoryAjaxController {
    @Resource
    CoolCategoryService service;
    @RequestMapping(value ="/coolCategoryVo")
    public JSONData selectCoolCategoryList(HttpServletRequest request)
    {
        JSONData data=new JSONData();
        List<CoolCategoryVo>list=service.selectCoolCategoryVoList();
        for (CoolCategoryVo coolCategoryVo : list) {
            for (CoolCategory coolCategory : coolCategoryVo.getCoolCategories()) {
                if(coolCategory.getImage()!=null&&!"".equals(coolCategory.getImage()))
                {
                    coolCategory.setImage(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/upload/" + coolCategory.getImage());
                }
            }
        }
        data.setData(list);
        return data;
    }
    @ExceptionHandler
    private JSONData exceptionHandle(Exception e)
    {
        JSONData data=new JSONData();
        data.setErrorCode(-20);
        if(e.getMessage()!=null&&!"".equals(e.getMessage()))
        {
            data.setMessage(e.getMessage());
        }else {
            data.setMessage(e.toString());
        }
        return data;
    }

}
