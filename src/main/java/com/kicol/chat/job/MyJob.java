package com.kicol.chat.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * @Author: 韩老魔
 * @Date: 2019/4/17 0017 20:27
 */
@Configuration
public class MyJob {
  /*  @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Scheduled(fixedDelay = 10000000)
    public void send(){
        System.out.println(System.currentTimeMillis()+"客户端定时任务");
        messagingTemplate.convertAndSend("/server/sendMessageByServer",new Date());
    }*/


}
