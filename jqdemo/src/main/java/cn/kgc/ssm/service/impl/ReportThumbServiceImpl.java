package cn.kgc.ssm.service.impl;

import cn.kgc.ssm.entity.ReportThumb;
import cn.kgc.ssm.entity.TryReport;
import cn.kgc.ssm.entity.User;
import cn.kgc.ssm.mybatis.ReportThumbMapper;
import cn.kgc.ssm.mybatis.TryReportMapper;
import cn.kgc.ssm.mybatis.UserMapper;
import cn.kgc.ssm.service.ReportThumbService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReportThumbServiceImpl implements ReportThumbService {
    @Resource
    ReportThumbMapper rtdao;
    @Resource
    UserMapper udao;
    @Resource
    TryReportMapper tdao;

    /**
     * 点赞
     * @param thumb
     * @return
     */
    @Override
    @Transactional
    public int insertReportThumb(ReportThumb thumb) {
        if(thumb==null)
        {
            throw new RuntimeException("请传递正确对象");
        }
        if(thumb.getUserId()==null)
        {
            throw new RuntimeException("请确保用户id存在");
        }
        if(thumb.getReportId()==null)
        {
            throw new RuntimeException("请确保报告id存在");
        }
        TryReport report =tdao.selectByPrimaryKey(thumb.getReportId());
        if(report==null)
        {
            throw new RuntimeException("没有对应id的报告,无法点赞");
        }
        User user=udao.selectByPrimaryKey(thumb.getUserId());
        if(user==null)
        {
            throw new RuntimeException("没有对应id的用户,无法点赞");
        }
        if(user.getState()<=0)
        {
            throw new RuntimeException(" 用户没有权限,无法点赞");
        }
        List<ReportThumb> list=rtdao.selectByUserAndReport(thumb);
        if(list.size()>0)
        {
            throw new RuntimeException("用户已经点赞,无法继续点赞");
        }
        return rtdao.insertSelective(thumb);
    }

    /**
     * 取消点赞
     * @param thumb
     * @return
     */
    @Override
    @Transactional
    public int deleteReportThumb(ReportThumb thumb) {
        if(thumb==null)
        {
            throw new RuntimeException("请传递正确对象");
        }
        if(thumb.getUserId()==null)
        {
            throw new RuntimeException("请确保用户id存在");
        }
        if(thumb.getReportId()==null)
        {
            throw new RuntimeException("请确保报告id存在");
        }
        TryReport report =tdao.selectByPrimaryKey(thumb.getReportId());
        if(report==null)
        {
            throw new RuntimeException("没有对应id的报告,无法点赞");
        }
        User user=udao.selectByPrimaryKey(thumb.getUserId());
        if(user==null)
        {
            throw new RuntimeException("没有对应id的用户,无法点赞");
        }
        if(user.getState()<=0)
        {
            throw new RuntimeException(" 用户没有权限,无法取消点赞");
        }
        List<ReportThumb> list=rtdao.selectByUserAndReport(thumb);
        int ret=0;
        if(list.size()>0)
        {
            ret=rtdao.deleteByPrimaryKey(list.get(0).getId());
        }else {
            throw new RuntimeException(" 用户尚未点赞,无法取消点赞");
        }
        return ret;
    }

    /**
     * 通过
     * @param thumb
     * @return
     */
    @Override
    public int selectByUserAndReport(ReportThumb thumb) {
        if(thumb==null)
        {
            throw new RuntimeException("请传递正确对象");
        }
        if(thumb.getUserId()==null)
        {
            throw new RuntimeException("请确保用户id存在");
        }
        if(thumb.getReportId()==null)
        {
            throw new RuntimeException("请确保报告id存在");
        }
        return rtdao.selectByUserAndReport(thumb).size();
    }

    /**
     * 删除或者添加点赞
     *
     * @param thumb
     * @return
     */
    @Override
    public int changeByUserAndReport(ReportThumb thumb) {
        int ret = selectByUserAndReport(thumb);
        if (ret > 0) {
            ret = deleteReportThumb(thumb);
            if (ret > 0) {
                return -1;
            } else {
                throw new RuntimeException("取消点赞失败");
            }
        } else {
            ret = insertReportThumb(thumb);
            if (ret > 0) {
                return 1;
            } else {
                throw new RuntimeException("点赞失败");
            }
        }
    }
}
