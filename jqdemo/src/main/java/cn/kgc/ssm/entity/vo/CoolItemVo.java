package cn.kgc.ssm.entity.vo;

import cn.kgc.ssm.entity.CoolItem;
import lombok.Data;

@Data
public class CoolItemVo extends CoolItem {
    private int thumbNum=0;
    private int commentNum=0;
}
