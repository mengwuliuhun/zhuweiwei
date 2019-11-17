package cn.kgc.ssm.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;
@Component
@ConfigurationProperties(prefix = "dog")
@Data
public class Dog {
    private String name;
    private Integer age;
}
