package cn.kgc.ssm.entity;

import cn.kgc.ssm.valid.group.UserLoginGroup;
import cn.kgc.ssm.valid.group.UserRegGroup;
import lombok.Data;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
@Data
public class User implements Serializable {
    private Integer id;
    @Pattern(regexp = "[1]\\d{10}",groups = {UserRegGroup.class},message = "手机号码要11位数字,且以1开头")
    private String phone;
    @Pattern(regexp = "[a-zA-z]\\w{1,11}",groups = {UserRegGroup.class},message = "名字以英文开头且为2到12位")
    @Pattern(regexp = "([a-zA-z]\\w{1,11})|([1]\\d{10})",groups = {UserLoginGroup.class},message = "名字以英文开头且为2到12位;手机号码要11位数字,且以1开头")
    private String name;
    @Pattern(regexp = "\\w{3,16}",groups = {UserRegGroup.class, UserLoginGroup.class},message = "密码要3到16位的字符")
    private String password;
    private String image;
    private Integer state;
}