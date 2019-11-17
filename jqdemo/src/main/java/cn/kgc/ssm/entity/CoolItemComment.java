package cn.kgc.ssm.entity;

import lombok.Data;

@Data
public class CoolItemComment {
    private Integer id;

    private Integer userId;

    private Integer itemId;

    private String comment;

    public Integer getId() {
        return id;
    }
}