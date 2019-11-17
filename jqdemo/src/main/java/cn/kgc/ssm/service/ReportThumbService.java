package cn.kgc.ssm.service;

import cn.kgc.ssm.entity.ReportThumb;

public interface ReportThumbService {
    /**
     * 点赞
     * @param thumb
     * @return
     */
    public int insertReportThumb(ReportThumb thumb);

    /**
     * 取消点赞
     * @param thumb
     * @return
     */
    int deleteReportThumb(ReportThumb thumb);

    /**
     * 通过
     * @param thumb
     * @return
     */
    int selectByUserAndReport(ReportThumb thumb);
    /**
     * 删除或者添加点赞
     * @param thumb
     * @return
     */
    int changeByUserAndReport(ReportThumb thumb);
}
