package cn.kgc.ssm.Controller;

import cn.kgc.ssm.entity.User;
import cn.kgc.ssm.service.UserService;
import cn.kgc.ssm.util.JSONData;
import cn.kgc.ssm.valid.group.UserRegGroup;
import com.github.pagehelper.PageInfo;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserAjaxController {
    private static final List<String> allows = Arrays.asList("image/png", "image/jpeg", "image/gif");
    private static final Long aLong = 1000 * 1024L;
    @Resource
    UserService service;

    @RequestMapping(value = "/user")
    public JSONData selectUserList(String name, String phone, @RequestParam(name = "pno", defaultValue = "1", required = true) Integer pno,
                                   @RequestParam(name = "psize", defaultValue = "4", required = true) Integer psize, HttpServletRequest request) {
        JSONData data = new JSONData();
        System.out.println("admin/user:  " + request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath());
        List<User> users = service.selectUserPagesLikeByNameAndPhone(name, phone, pno, psize);
        for (User user : users) {
            if (user.getImage() != null && !"".equals(user.getImage())) {
                user.setImage(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/upload/" + user.getImage());
            }
        }
        PageInfo<User> pageInfo = new PageInfo<>(users);
        data.setData(pageInfo);
        return data;
    }

    @RequestMapping(value = "/updateUser")
    public JSONData updateUser(@Validated({UserRegGroup.class}) User user, Errors errors, MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        JSONData data = new JSONData();
        User user1=new User();
        user1.setId(user.getId());
        if (errors.hasErrors()) {
            data.setErrorCode(-1);
            data.setMessage(errors.getFieldErrors().get(0).getDefaultMessage());
            return data;
        }
        //        文件验证
//        验证文件格式
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
        user.setImage(fileName);
        File path = new File(request.getServletContext().getRealPath("upload"));
        if (!path.exists()) {
            path.mkdirs();
        }
        try {
            int ret = service.updateOrInsertUser(user);
            if (ret > 0) {
                File savePath = new File(path, fileName);
                file.transferTo(savePath);
                if (user1.getId() == null) {
                    data.setMessage("添加成功");
                } else {
                    data.setMessage("修改成功");
                }

                return data;
            } else {
                data.setErrorCode(-8);
                if (user1.getId() == null) {
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

    @RequestMapping(value = "/delete")
    public JSONData deleteUser(@RequestParam(name = "id", defaultValue = "0", required = true) Integer id) {
        System.out.println("AdminUserAjaxController delete");
        JSONData data = new JSONData();
        int i = service.deleteUser(id);
        if (i > 0) {
            data.setMessage("删除成功");
        } else {
            data.setErrorCode(-3);
            data.setMessage("删除失败");
        }
        return data;
    }

    @RequestMapping(value = "/recover")
    public JSONData updateUser(@RequestParam(name = "id", defaultValue = "0", required = true) Integer id) {
        JSONData data = new JSONData();
        int i = service.recoverUser(id);
        if (i > 0) {
            data.setMessage("恢复成功");
        } else {
            data.setMessage("恢复失败");
            data.setErrorCode(-3);
        }
        return data;
    }

    @RequestMapping(value = "/selectUser")
    public JSONData selectUser(@RequestParam(name = "id", defaultValue = "0", required = true) Integer id, HttpServletRequest request) {
        JSONData data = new JSONData();
        User user = service.selectUser(id);
        System.out.println("AdminUserAjaxController   " + user);
        if (user != null) {
            data.setMessage("查询成功");
            user.setImage(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/upload/" + user.getImage());
            data.setData(user);
        } else {
            data.setMessage("查询失败");
            data.setErrorCode(-3);
        }
        return data;
    }
}