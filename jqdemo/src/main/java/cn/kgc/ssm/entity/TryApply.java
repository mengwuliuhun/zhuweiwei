package cn.kgc.ssm.entity;

import lombok.Data;

import java.util.Date;
@Data
public class TryApply {
    private Integer id;

    private Integer userId;

    private Integer itemId;

    private Date applyDate;
}