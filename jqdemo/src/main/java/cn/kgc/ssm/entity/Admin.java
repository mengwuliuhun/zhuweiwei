package cn.kgc.ssm.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Admin {
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String password;

    private Integer state;
}