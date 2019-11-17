package cn.kgc.ssm.Controller;

import cn.kgc.ssm.entity.vo.TryItemVo;
import cn.kgc.ssm.service.TryItemService;
import cn.kgc.ssm.util.JSONData;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TryItemAjaxController {
    @Resource
    TryItemService service;
    @RequestMapping(value ="/tryItemVo_ajax")
    public JSONData selectCoolItemVoList(String category, String type, @RequestParam(name = "pno",defaultValue = "1",required =true) Integer pno,
                                         @RequestParam(name = "psize",defaultValue = "4",required = true) Integer psize)
    {
        JSONData data=new JSONData();
        List<TryItemVo>list=service.selectTryItemsByCategoryOrType(category,type,pno,psize);
        PageInfo<TryItemVo> voPageInfo=new PageInfo<>(list);
        data.setData(voPageInfo);
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
