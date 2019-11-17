package cn.kgc.ssm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;
@Data
public class Guide {
    private Integer id;
    @NotBlank
    private String title;
    private String image;
    @JsonFormat(pattern="yyyy-MM-dd ",timezone = "GMT+8")
    private Date issueDate;
}