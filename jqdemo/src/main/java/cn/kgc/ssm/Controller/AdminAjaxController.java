package cn.kgc.ssm.Controller;

import cn.kgc.ssm.entity.Admin;
import cn.kgc.ssm.service.AdminService;
import cn.kgc.ssm.util.JSONData;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class AdminAjaxController {
    @Resource
    AdminService service;
    @RequestMapping(value = "admin/isAdmin_ajax", method = {RequestMethod.GET})
    public JSONData adminCheck(HttpServletRequest req) throws Exception {
        JSONData data = new JSONData();
        return data;
    }
    @RequestMapping(value = "/adminExit_ajax", method = {RequestMethod.GET})
    public JSONData adminExit(HttpServletRequest req) throws Exception {
        JSONData data = new JSONData();
        req.getSession().removeAttribute("admin");
        data.setMessage("管理员退出,返回用户界面");
        return data;
    }
    @RequestMapping(value = "/aLogin_ajax", method = {RequestMethod.GET})
    public JSONData adminLogin(@Valid Admin admin, Errors errors, HttpServletRequest req) throws Exception {
        JSONData data = new JSONData();
        if (errors.hasErrors()) {
            data.setErrorCode(-1);
            data.setMessage(errors.getFieldErrors().get(0).getDefaultMessage());
            return data;
        }
        Admin admin1=service.selectByNameAndPassword(admin);
        if(admin1!=null)
        {
            data.setMessage("管理员登陆成功");
            req.getSession().setAttribute("admin",admin1);
            return data;
        }else {
            data.setErrorCode(-8);
            data.setMessage("管理员不存在");
            return data;
        }
    }
}
