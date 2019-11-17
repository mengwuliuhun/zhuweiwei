package cn.kgc.ssm.entity.vo;

import cn.kgc.ssm.entity.TryItem;
import lombok.Data;

@Data
public class TryItemVo extends TryItem {
    private Integer acnt=0;
    private Integer rcnt=0;
    private String state;
    private Integer remainDays;

    @Override
    public String toString() {
        return "TryItemVo{" +super.toString()+
                "acnt=" + acnt +
                ", rcnt=" + rcnt +
                ", state='" + state + '\'' +
                ", remainDays=" + remainDays +
                '}';
    }
}
