package cn.kgc.ssm.util;

import java.util.Date;

public class DateUtils {
    private static long MS_PER_DAY=1000*60*60*24;
    private static long MS_TIMEZONE=1000*60*60*8;

    /**
     * 得到当前时区时间
     * @return
     */
    public static Date getNow(){
        Date now=new Date(
                System.currentTimeMillis()/MS_PER_DAY*MS_PER_DAY-MS_TIMEZONE);
        return now;
    }
    /**
     * 获取两个日期之间相差的天数
     * @param d1
     * @param d2
     * @return
     */
    public static Integer daysBetween(Date d1, Date d2)
    {

        long t1=d1.getTime();
        long t2=d2.getTime();
        return (int)(Math.abs(t2-t1)/MS_PER_DAY);
    }
}
