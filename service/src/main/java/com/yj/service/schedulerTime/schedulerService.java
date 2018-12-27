package com.yj.service.schedulerTime;

import com.yj.service.service.IFrCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时任务
 */

@Component
public class schedulerService {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private final Logger log = LoggerFactory.getLogger(schedulerService.class);

    @Resource
    private IFrCardService iFrCardService;


    //每隔2秒执行一次
    @Scheduled( cron =  "0 0/60 * * * ?")
    public void testTasks() {
        log.info("每隔1小时执行一次会员卡停卡，补余信息更新：" + dateFormat.format(new Date()));
        try{
            iFrCardService.toUpdateCardStop();
        }catch (Exception e){
            log.info("定时任务执行时间异常：" +e);
        }
    }

    //每天凌晨01分的时候执行-----------开卡
    @Scheduled(cron = "0 01 00 ? * *")
    public void updateOpenCard() {
        log.info("定时任务执行时间：" + dateFormat.format(new Date()));
        try{
            iFrCardService.updateCardTime();
        }catch (Exception e){
            log.info("定时任务执行时间异常：" +e);
        }
    }


}
