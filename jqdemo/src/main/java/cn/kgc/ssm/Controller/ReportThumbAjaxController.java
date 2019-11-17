package cn.kgc.ssm.Controller;

import cn.kgc.ssm.entity.ReportThumb;
import cn.kgc.ssm.entity.User;
import cn.kgc.ssm.service.ReportThumbService;
import cn.kgc.ssm.util.JSONData;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class ReportThumbAjaxController {
    @Resource
    ReportThumbService service;

    @RequestMapping(value = "/changeReoprtThumb")
    public JSONData insertCoolItem(ReportThumb thumb, HttpServletRequest request) {
        JSONData data = new JSONData();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            data.setErrorCode(-2);
            data.setMessage("用户尚未登录");
            return data;
        }
        thumb.setUserId(user.getId());
        System.out.println("GuideThumbAjaxController  "+thumb);
        try {
            int ret = service.changeByUserAndReport(thumb);
            if (ret == 1) {
                data.setMessage("点赞成功");
                return data;
            } else if (ret == -1) {
                data.setMessage("取消点赞成功");
                return data;
            } else {
                throw new RuntimeException("操作失败失败");
            }

        } catch (Exception e) {
            data.setErrorCode(-8);
            String ss=e.getMessage();
            data.setMessage(e.getMessage());
            if(ss==null||"".equals(ss))
            {
                data.setMessage(e.toString());
            }
            return data;
        }
    }

    @ExceptionHandler
    private JSONData exceptionHandle(Exception e) {
        JSONData data = new JSONData();
        data.setErrorCode(-20);
        if (e.getMessage() != null && !"".equals(e.getMessage())) {
            data.setMessage(e.getMessage());
        } else {
            data.setMessage(e.toString());
        }
        return data;
    }

}
