package com.qw.config;

import com.qw.service.OrderService;
import com.qw.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderJob {

    @Autowired
    private OrderService orderService;

    /**
     * problem with scheduled close
     * time diff
     */
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void autoCloseOrder(){
//        System.out.println("Executing timed task, current time is " + DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
        orderService.closeOrder();
    }
}
