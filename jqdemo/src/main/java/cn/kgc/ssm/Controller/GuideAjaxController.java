package cn.kgc.ssm.Controller;

import cn.kgc.ssm.entity.vo.GuideVo;
import cn.kgc.ssm.service.GuideService;
import cn.kgc.ssm.util.JSONData;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class GuideAjaxController {
    @Resource
    GuideService service;
    @RequestMapping(value ="/sGuideVL_ajax")
    public JSONData selectCoolItemVoList(String th, @RequestParam(name = "pno",defaultValue = "1",required =true) Integer pno,
                                         @RequestParam(name = "psize",defaultValue = "12",required = true) Integer psize, HttpServletRequest request)
    {
        JSONData data=new JSONData();
        List<GuideVo> vos=service.selectGuideVoList(th,pno,psize);
        for (GuideVo vo : vos) {
            vo.setImage(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/upload/" + vo.getImage());
        }
        PageInfo<GuideVo> info=new PageInfo<>(vos);
        data.setData(info);
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
