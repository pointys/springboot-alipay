package com.kicol.chat.model;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 韩老魔
 * 消息类
 * @Date: 2019/4/17 0017 19:40
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {
    private String name; //发送人
    private String content; //发送消息
}
