package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.util.DateUtil;
import com.yj.dal.dao.FrEducationFreezeClientMapper;
import com.yj.dal.dao.FrEducationPublicMapper;
import com.yj.dal.model.FrEducationFreezeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * className FrEducationPublicThreadPool
 * by Kylan
 *
 * @author Kylan
 * @date 2019-01-25 09:31
 */
@Service
public class FrEducationPublicThreadPool {


    private ScheduledExecutorService scheduledExecutorService;

    @Autowired
    private FrEducationPublicMapper frEducationPublicMapper;

    @Autowired
    private FrEducationFreezeClientMapper frEducationFreezeClientMapper;


    @PostConstruct
    public void startSchedule(){
        scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.scheduleAtFixedRate(new ChangeEduStatusRunnable(frEducationPublicMapper, frEducationFreezeClientMapper), 2, 10 , TimeUnit.MINUTES);
    }

    @PreDestroy
    public void endSchedule(){
        scheduledExecutorService.shutdownNow();
    }

    public class ChangeEduStatusRunnable implements Runnable{


        private FrEducationPublicMapper frEducationPublicMapper;
        private FrEducationFreezeClientMapper frEducationFreezeClientMapper;

        public ChangeEduStatusRunnable(FrEducationPublicMapper frEducationPublicMapper, FrEducationFreezeClientMapper frEducationFreezeClientMapper){
            this.frEducationPublicMapper = frEducationPublicMapper;
            this.frEducationFreezeClientMapper = frEducationFreezeClientMapper;
        }

        @Override
        public void run() {
            //调度 修改课程状态为完成，上课完成
            List<Map<String, String>> list = frEducationPublicMapper.findEduIdForEndClass();
            String eduId = "";
            Date now = new Date();
            for(Map<String, String> map: list){
                eduId = map.get("eduId");
                frEducationPublicMapper.updateEduStatusToEndClass(eduId);
            }
            //修改冻结用户
            List<FrEducationFreezeClient> frEducationFreezeClients = frEducationFreezeClientMapper.selectList(new EntityWrapper<>());
            for(FrEducationFreezeClient frEducationFreezeClient: frEducationFreezeClients){
                if(now.getTime() - frEducationFreezeClient.getEndDate().getTime() > 0){
                    frEducationFreezeClientMapper.deleteById(frEducationFreezeClient.getId());
                    continue;
                }
                if(null == frEducationFreezeClient.getUnFreezeDate()){
                    continue;
                }
                if(now.getTime() - frEducationFreezeClient.getUnFreezeDate().getTime() > 0){
                    frEducationFreezeClientMapper.deleteById(frEducationFreezeClient.getId());
                    continue;
                }
            }
            //修改状态
            this.changeEducationStatus();
        }

        /**
         * 开课时间超过一天，则改成已结束
         */
        private void changeEducationStatus(){
            //当前时间
            Date now = new Date();
            String time = "";
            Calendar cl = Calendar.getInstance();
            cl.setTime(now);
            cl.add(Calendar.DATE, -1);
            now = cl.getTime();
            time = DateUtil.dateToString(now, "yyyy-MM-dd");
            frEducationPublicMapper.updateOfTimeOutStatus(time);
        }
    }


}
