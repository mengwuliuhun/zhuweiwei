package cn.kgc.ssm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class TryReport {
    private Integer id;

    private String title;

    private String image;

    private Integer applyId;
    @JsonFormat(pattern="yyyy-MM-dd ",timezone = "GMT+8")
    private Date pubDate;
}