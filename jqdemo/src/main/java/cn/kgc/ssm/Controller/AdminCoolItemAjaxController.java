package cn.kgc.ssm.Controller;

import cn.kgc.ssm.entity.CoolItem;
import cn.kgc.ssm.service.CoolItemService;
import cn.kgc.ssm.util.JSONData;
import com.github.pagehelper.PageInfo;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminCoolItemAjaxController {
    private static final List<String> allows = Arrays.asList("image/png", "image/jpeg", "image/gif");
    private static final Long aLong = 1000 * 1024L;
    @Resource
    CoolItemService service;

    @RequestMapping(value = "/coolItem")
    public JSONData selectUserList(String name, String title, @RequestParam(name = "pno", defaultValue = "1", required = true) Integer pno,
                                   @RequestParam(name = "psize", defaultValue = "4", required = true) Integer psize, HttpServletRequest request) {
        JSONData data = new JSONData();
        List<CoolItem> coolItems = service.selectCoolItPagesLikeByNameAndTitle(name, title, pno, psize);
        for (CoolItem coolItem : coolItems) {
            if (coolItem.getImage() != null && !"".equals(coolItem.getImage())) {
                coolItem.setImage(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/upload/" + coolItem.getImage());
            }
        }
        PageInfo<CoolItem> pageInfo = new PageInfo<>(coolItems);
        data.setData(pageInfo);
        return data;
    }

    @RequestMapping(value = "/deleteCi")
    public JSONData deleteUser(@RequestParam(name = "id", defaultValue = "0", required = true) Integer id) {
        System.out.println("AdminUserAjaxController delete");
        JSONData data = new JSONData();
//        int i=service.deleteUser(id);
//        if(i>0)
//        {
//            data.setMessage("删除成功");
//        }else {
//            data.setErrorCode(-3);
//            data.setMessage("删除失败");
//        }
        return data;
    }

    @RequestMapping(value = "/selectCi")
    public JSONData selectUser(@RequestParam(name = "id", defaultValue = "0", required = true) Integer id, HttpServletRequest request) {
        JSONData data = new JSONData();
        CoolItem item = service.selectCoolIt(id);
        if (item != null) {
            data.setMessage("查询成功");
            item.setImage(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/upload/" + item.getImage());
            data.setData(item);
        } else {
            data.setMessage("查询失败");
            data.setErrorCode(-3);
        }
        return data;
    }

    @RequestMapping(value = "/updateCoolIt")
    public JSONData updateCoolItem(@Valid CoolItem item, Errors errors, MultipartFile file, HttpServletRequest request) {
        JSONData data = new JSONData();

        if (errors.hasErrors()) {
            data.setErrorCode(-1);
            data.setMessage(errors.getFieldErrors().get(0).getDefaultMessage());
            return data;
        }
        if(item.getPrice()==null)
        {
            throw new RuntimeException("金额不能为空");
        }
        if(item.getPrice()<=0)
        {
            throw new RuntimeException("金额要大于零");
        }
        //       文件验证
//       验证文件格式
        String type = file.getContentType();
        if (!allows.contains(type)) {
            data.setErrorCode(-2);
            data.setMessage("文件格式不支持");
            return data;
        }
//        验证文件大小
        Long size = file.getSize();
        if (size > aLong) {
            data.setErrorCode(-2);
            data.setMessage("文件超过指定大小");
            return data;
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        item.setImage(fileName);
        File path = new File(request.getServletContext().getRealPath("upload"));
        if (!path.exists()) {
            path.mkdirs();
        }
        try {
            int ret = service.updateOrInsertUser(item);
            if (ret > 0) {
                File savePath = new File(path, fileName);
                file.transferTo(savePath);
                if (item.getId() == null) {
                    data.setMessage("添加成功");
                } else {
                    data.setMessage("修改成功");
                }

                return data;
            } else {
                data.setErrorCode(-8);
                if (item.getId() == null) {
                    data.setMessage("添加用户失败");
                } else {
                    data.setMessage("修改失败");
                }
                return data;
            }

        } catch (Exception e) {
            data.setErrorCode(-5);
            data.setMessage("出现异常,异常为:" + e.getMessage());
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