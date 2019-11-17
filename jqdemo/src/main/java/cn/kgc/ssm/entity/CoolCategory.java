package cn.kgc.ssm.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CoolCategory {
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String category;

    private Integer quantity;

    private String image;
}