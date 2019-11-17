package cn.kgc.ssm.Controller;

import cn.kgc.ssm.entity.Admin;
import cn.kgc.ssm.service.AdminService;
import cn.kgc.ssm.util.JSONData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/rest")
@Api(value = "测试",tags = "测试rest")
public class RestTestController {
    @ApiOperation(value = "通过id测试get方法")
    @ApiImplicitParams({@ApiImplicitParam(name = "id",value = "正整数",required=true )})
    @RequestMapping(value = "/test1/{id}",
            method = RequestMethod.GET)
    public JSONData select(@PathVariable Integer id) {
        JSONData data = new JSONData();
        data.setMessage("get " + id);
        return data;
    }

    @RequestMapping(value = "/test1/{id}",
            method = RequestMethod.POST)
    public JSONData insert(@PathVariable Integer id) {
        JSONData data = new JSONData();
        data.setMessage("post" + id);
        return data;
    }

    @RequestMapping(value = "/test1/{id}",
            method = RequestMethod.PUT)
    public JSONData update(@PathVariable Integer id) {
        JSONData data = new JSONData();
        data.setMessage("update" + id);
        return data;
    }

    @RequestMapping(value = "/test1/{id}",
            method = RequestMethod.DELETE)
    public JSONData test1(@PathVariable Integer id) {
        JSONData data = new JSONData();
        data.setMessage("delete" + id);
        return data;
    }
}
