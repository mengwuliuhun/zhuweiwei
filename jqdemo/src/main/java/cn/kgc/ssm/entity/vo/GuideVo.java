package cn.kgc.ssm.entity.vo;

import cn.kgc.ssm.entity.CoolItem;
import cn.kgc.ssm.entity.Guide;
import lombok.Data;

@Data
public class GuideVo extends Guide {
    private int thumbNum=0;
    private int commentNum=0;
}
