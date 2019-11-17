package cn.kgc.ssm.entity;

import lombok.Data;

@Data
public class GuideThumb {
    private Integer id;

    private Integer userId;

    private Integer guideId;

    public Integer getId() {
        return id;
    }
}