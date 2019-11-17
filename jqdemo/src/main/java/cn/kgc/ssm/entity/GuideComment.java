package cn.kgc.ssm.entity;

import lombok.Data;

@Data
public class GuideComment {
    private Integer id;

    private Integer userId;

    private Integer guideId;

    private String comment;
}