package com.kicol.chat.controller;

import com.kicol.chat.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;

/**
 * @Author: 韩老魔
 * @Date: 2019/4/17 0017 19:26
 */
@Controller
public class WebSocketController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @RequestMapping("chat")
    public String chat(){
        return "chat";
    }
    /**
     * 群发
     * @param message
     * @return
     * @throws Exception
     */
    @MessageMapping("/hello") //接收/app/hello路径发来的信息
    @SendTo("/topic/greetings")//接收上面路径发来的消息后在发送到定义的路径上 即topic而topic会被代理进行广播
    public Message messageHandling(Message message) throws Exception {
        return message;
    }

}
