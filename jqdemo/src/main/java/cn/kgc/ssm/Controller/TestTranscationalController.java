package cn.kgc.ssm.Controller;

import cn.kgc.ssm.entity.Admin;
import cn.kgc.ssm.service.AdminService;
import cn.kgc.ssm.service.UserService;
import cn.kgc.ssm.util.JSONData;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Map;

@Controller
public class TestTranscationalController {

    @RequestMapping(value = "/tt")
public String test(Map<String,Object> map)
{
    map.put("hello","<h1>您好</h1>");
    map.put("users", Arrays.asList("zhangsan","lisi","wangwu"));
    return "suc";
}
}
