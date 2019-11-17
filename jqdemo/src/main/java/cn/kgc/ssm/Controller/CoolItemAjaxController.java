package cn.kgc.ssm.Controller;

import cn.kgc.ssm.entity.vo.CoolItemVo;
import cn.kgc.ssm.service.CoolItemService;
import cn.kgc.ssm.util.JSONData;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Api(value = "产品信息",tags = "获取产品相关信息")
public class CoolItemAjaxController {
    @Resource
    CoolItemService service;
    @ApiOperation(value = "分页得到产品信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "pno",value = "正整数",required=true ),@ApiImplicitParam(name = "psize",value = "正整数",required=true )})
    @RequestMapping(value ="/sCoolIVL_ajax",method = RequestMethod.GET)
    public JSONData selectCoolItemVoList(@ApiIgnore String th, @RequestParam(name = "pno",defaultValue = "1",required =true) Integer pno,
                                        @RequestParam(name = "psize",defaultValue = "12",required = true) Integer psize,HttpServletRequest request)
    {
        JSONData data=new JSONData();
        System.out.println("sCoolIVL_ajax  "+th+pno+psize);
        List<CoolItemVo> vos=service.selectCoolItemVoList(th,pno,psize);
        for (CoolItemVo vo : vos) {
            vo.setImage(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/upload/" + vo.getImage());
        }
        PageInfo<CoolItemVo> info=new PageInfo<>(vos);
        data.setData(info);
        return data;
    }
    @ExceptionHandler
    private JSONData exceptionHandle(Exception e)
    {
        JSONData data=new JSONData();
        data.setErrorCode(-27);
        if(e.getMessage()!=null&&!"".equals(e.getMessage()))
        {
            data.setMessage(e.getMessage());
        }else {
            data.setMessage(e.toString());
        }
        return data;
    }


}
