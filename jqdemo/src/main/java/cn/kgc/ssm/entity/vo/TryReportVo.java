package cn.kgc.ssm.entity.vo;
import cn.kgc.ssm.entity.CoolItem;
import cn.kgc.ssm.entity.TryReport;
import cn.kgc.ssm.entity.User;
import lombok.Data;

@Data
public class TryReportVo extends TryReport {
    private int thumbNum=0;
    private int commentNum=0;
    private String userId;
    private User user;
    private TryItemVo vo;
}
