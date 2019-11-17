package cn.kgc.ssm;

import cn.kgc.ssm.entity.User;
import cn.kgc.ssm.mybatis.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JqdemoApplicationTests {
    //操作k-v都是字符串
//    @Resource
//    StringRedisTemplate st;
//    //k-v都是对象的
//    @Autowired
//    @Qualifier("redisTemplate")
//    RedisTemplate tt;
//    @Resource
//    UserMapper mapper;

    /**
     * redis常见五大数据类型
     * String,List,Set,Hash,ZSet
     */
//    @Test
//    public void redisTest()
//    {
////        字符串
//        st.opsForValue().append("msg","world");
//    }

    @Resource
    JavaMailSenderImpl sender;

    @Test
    public void testLogging() throws MessagingException {
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);


        helper.setSubject("hello");
        helper.setText("<b style='color:red'>今天30开会</b>",true);
        helper.setTo("qwerty579375@163.com");
        helper.setFrom("1007493965@qq.com");
//        上传文件
        helper.addAttachment("1.jpg",new File("F:\\多媒体\\狐狸.jpg"));
        sender.send(mimeMessage);
    }


}
