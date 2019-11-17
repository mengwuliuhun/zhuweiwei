package cn.kgc.ssm.Controller;

import cn.kgc.ssm.entity.User;
import cn.kgc.ssm.service.UserService;
import cn.kgc.ssm.util.JSONData;
import cn.kgc.ssm.valid.group.UserLoginGroup;
import cn.kgc.ssm.valid.group.UserRegGroup;
import io.swagger.annotations.Api;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
@RestController
public class UserAjaxController {
    @Resource
    UserService service;

    /**
     * 用户ajax注册
     *
     * @param user
     * @param errors
     * @param repassword
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "/reg_ajax", method = {RequestMethod.POST})
    public JSONData reg(@Validated(UserRegGroup.class) User user, Errors errors, String code,
                        String repassword, HttpServletRequest req) throws Exception {
        System.out.println("UserAjaxController reg_ajax "+user);
        JSONData data = new JSONData();
        if (errors.hasErrors()) {
            data.setErrorCode(-1);
            data.setMessage(errors.getFieldErrors().get(0).getDefaultMessage());
            return data;
        }
        if (!user.getPassword().equals(repassword)) {
            data.setErrorCode(-2);
            data.setMessage("密码和确认密码不一致");
            return data;
        }
        String codes = (String) req.getSession().getAttribute("code");
        if (!codes.equalsIgnoreCase(code)) {
            data.setErrorCode(-2);
            data.setMessage("验证码错误,登录失败");
            return data;
        }
        try {
            User users = service.register(user);
            data.setMessage("注册成功,跳转首页");
            return data;
        } catch (Exception e) {
            data.setErrorCode(-5);
            data.setMessage("查询用户出现异常,异常为:" + e.getMessage());
            return data;
        }
    }

    /**
     * 用户登录ajax
     *
     * @param user
     * @param errors
     * @param req
     * @return
     * @throws Exception
     */

    @RequestMapping("/log_ajax")
    public JSONData log(@Validated(UserLoginGroup.class) User user, Errors errors, String code, HttpServletRequest req) throws Exception {
        JSONData data = new JSONData();
        if (errors.hasErrors()) {
            data.setErrorCode(-7);
            data.setMessage(errors.getFieldErrors().get(0).getDefaultMessage());
            return data;
        }
        try {
            String codes = (String) req.getSession().getAttribute("code");
            if (!codes.equalsIgnoreCase(code)) {
                data.setErrorCode(-2);
                data.setMessage("验证码错误,登录失败");
                return data;
            }

            User users = service.login(user);
            System.out.println("UserAjaxController   "+users);
            if (users != null) {
                req.getSession().setAttribute("user", users);
                data.setMessage("用户登录成功,跳转首页");
                data.setData(users);
                return data;
            } else {
                data.setErrorCode(-3);
                data.setMessage("用户不存在或者被禁言,登录失败");
                return data;
            }

        } catch (Exception e) {
            data.setErrorCode(-2);
            data.setMessage("用户查询失败,登录失败");
            return data;
        }
    }

    @RequestMapping(value = "/regName_ajax", method = {RequestMethod.GET})
    public JSONData regCheckName(User user
            , HttpServletRequest req) throws Exception {
        System.out.println("UserAjaxController  regName_ajax  "+user);
        JSONData data = new JSONData();
        if(service.selectUserByName(user)!=0)
        {
            data.setErrorCode(-8);
            data.setMessage("用户名已经存在");
        }
        return data;
    }
    @RequestMapping(value = "/regPhone_ajax", method = {RequestMethod.GET})
    public JSONData regCheckPhone(User user
            , HttpServletRequest req) throws Exception {
        System.out.println("UserAjaxController  regPhone_ajax   "+user);
        JSONData data = new JSONData();
        if(service.selectUserByPhone(user)!=0)
        {
            data.setErrorCode(-8);
            data.setMessage("电话已经存在");
        }
        return data;
    }
    @RequestMapping(value = "/logPhoneName_ajax", method = {RequestMethod.GET})
    public JSONData regCheckPhoneAndName(String name
            , HttpServletRequest req) throws Exception {
        JSONData data = new JSONData();
        User user=new User();
        User user1=new User();
        user.setName(name);
        user1.setPhone(name);
        if(service.selectByNameAndPhone(user,user1)>0)
        {
            data.setErrorCode(-8);
            data.setMessage("电话或者用户名不存在");

        }
        return data;
    }
    @RequestMapping(value = "/loadIndex_ajax", method = {RequestMethod.GET})
    public JSONData loadIndex(HttpServletRequest req) throws Exception {
        JSONData data = new JSONData();
        User user= (User) req.getSession().getAttribute("user");
        System.out.println("loadIndex_ajax user为:"+user);
        if(user==null)
        {
            data.setErrorCode(-5);
        }
        System.out.println(data);
        return data;
    }
    @RequestMapping(value = "/exit_ajax", method = {RequestMethod.GET})
    public JSONData userExit(HttpServletRequest req) throws Exception {
        JSONData data = new JSONData();
        req.getSession().removeAttribute("user");
        req.getSession().invalidate();
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
