package cn.kgc.ssm;

import cn.kgc.ssm.entity.Dog;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@MapperScan(value = "cn.kgc.ssm.mybatis")
@EnableScheduling
@SpringBootApplication
@RestController
public class JqdemoApplication {

   public void test()
   {

   }
    public static void main(String[] args) {
        SpringApplication.run(JqdemoApplication.class, args);
        new JqdemoApplication().test();

    }

}
