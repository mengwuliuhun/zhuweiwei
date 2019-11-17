package cn.kgc.ssm.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TryItem {
    private Integer id;

    private String title;

    private String image;

    private Integer quantity;

    private String category;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss.SS")
    private Date beginDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss.SS")
    private Date endDate;

    private Double price;
}