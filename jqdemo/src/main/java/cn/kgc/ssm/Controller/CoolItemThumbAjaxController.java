package cn.kgc.ssm.Controller;

import cn.kgc.ssm.entity.CoolItemThumb;
import cn.kgc.ssm.entity.User;
import cn.kgc.ssm.service.CoolItemThumbService;
import cn.kgc.ssm.util.JSONData;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class CoolItemThumbAjaxController {
    @Resource
    CoolItemThumbService service;
    @RequestMapping(value ="/addCoolIT")
    public JSONData insertCoolItem(CoolItemThumb thumb, HttpServletRequest request)
    {
        JSONData data=new JSONData();
        User user= (User) request.getSession().getAttribute("user");
        if(user==null)
        {
            data.setErrorCode(-2);
            data.setMessage("用户尚未登录");
            return data;
        }
        thumb.setUserId(user.getId());
        try{
            int ret=service.insertCoolIt(thumb);
            if(ret>0)
            {
                data.setMessage("点赞成功");
                return data;
            }else {
                throw new RuntimeException("点赞失败");
            }
        }catch (Exception e)
        {
            data.setErrorCode(-8);
            data.setMessage(e.getMessage());
            return data;
        }
    }
    @RequestMapping(value ="/selectCoolIT")
    public JSONData selectCoolItem(CoolItemThumb thumb, HttpServletRequest request)
    {
        JSONData data=new JSONData();
        User user= (User) request.getSession().getAttribute("user");
        if(user==null)
        {
            data.setErrorCode(-2);
            data.setMessage("用户尚未登录");
            return data;
        }
        thumb.setUserId(user.getId());
        try{
            int ret=service.selectByUserAndCoolItem(thumb);
            if(ret>0)
            {
                data.setMessage("have");
                return data;
            }else {
                data.setMessage("no");
                return data;
            }
        }catch (Exception e)
        {
            data.setErrorCode(-8);
            data.setMessage(e.getMessage());
            return data;
        }
    }
    @RequestMapping(value ="/deleteCoolIT")
    public JSONData deleteCoolItem(CoolItemThumb thumb, HttpServletRequest request)
    {
        JSONData data=new JSONData();
        User user= (User) request.getSession().getAttribute("user");
        if(user==null)
        {
            data.setErrorCode(-2);
            data.setMessage("用户尚未登录");
            return data;
        }
        thumb.setUserId(user.getId());
        try{
            int ret=service.deleteCoolIt(thumb);
            if(ret>0)
            {
                data.setMessage("取消点赞成功");
                return data;
            }else {
                throw new RuntimeException("取消点赞失败");
            }
        }catch (Exception e)
        {
            data.setErrorCode(-8);
            data.setMessage(e.getMessage());
            return data;
        }
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
