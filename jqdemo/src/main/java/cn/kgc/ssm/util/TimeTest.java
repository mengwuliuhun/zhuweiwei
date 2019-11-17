package cn.kgc.ssm.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TimeTest {
//    private int i=0;
    /**
     * 秒,分,时,日,月,周几
     * *****MON-SAT
     */
//    @Scheduled(cron ="2/4 * * * * SUN-SAT" )
    public void timeTest()
    {
        System.out.println("hello");
    }
}
