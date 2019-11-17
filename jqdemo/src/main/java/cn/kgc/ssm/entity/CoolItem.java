package cn.kgc.ssm.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class CoolItem {
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String title;
    @NumberFormat(pattern = "#0.0")
    private Double price=0.0;
    private String image;
    @JSONField(format = "yyyy-MM-dd ")
    @JsonFormat(pattern="yyyy-MM-dd ",timezone = "GMT+8")
    private Date pubDate;
}