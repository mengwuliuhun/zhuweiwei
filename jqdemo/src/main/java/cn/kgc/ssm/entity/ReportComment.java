package cn.kgc.ssm.entity;

import lombok.Data;

@Data
public class ReportComment {
    private Integer id;

    private Integer userId;

    private Integer reportId;

    private String comment;
}