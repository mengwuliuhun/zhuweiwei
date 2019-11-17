package cn.kgc.ssm.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

@Data
public class CoolItemThumb {
    private Integer id;
    private Integer userId;
    private Integer itemId;
}