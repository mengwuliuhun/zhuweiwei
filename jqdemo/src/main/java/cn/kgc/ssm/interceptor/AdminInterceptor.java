package cn.kgc.ssm.interceptor;

import cn.kgc.ssm.entity.Admin;
import cn.kgc.ssm.util.JSONData;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("AdminInterceptor  preHandle");
        Admin admin= (Admin) httpServletRequest.getSession().getAttribute("admin");
        httpServletResponse.setCharacterEncoding("utf-8");
        if(admin==null)
        {
            PrintWriter out=httpServletResponse.getWriter();
            JSONData data=new JSONData();
            data.setMessage("管理员尚未登录");
            data.setErrorCode(-999);
            out.print(JSON.toJSON(data));
            out.close();
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle FirstInterceptor");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion FirstInterceptor");
    }
}
