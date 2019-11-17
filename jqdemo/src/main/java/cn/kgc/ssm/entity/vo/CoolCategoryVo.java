package cn.kgc.ssm.entity.vo;

import cn.kgc.ssm.entity.CoolCategory;
import lombok.Data;

import java.util.List;
@Data
public class CoolCategoryVo extends CoolCategory {
    private String category;
    private List<CoolCategory>coolCategories;
}
