package com.kicol.controller;

import com.kicol.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: 韩老魔
 * @Date: 2019/5/11 0011 14:29
 */
@Controller
public class ThyController {
    @RequestMapping("test")
    public String test(Model model){

        List<User> list=new ArrayList<>();
        for(int i=0;i<5;i++){
            User u=new User();
            u.setUserId(0+i);
            u.setUserName("韩浩辰"+i);
            list.add(u);
        }
        for (User u1:
             list) {
            System.out.println(u1.toString());
        }
        model.addAttribute("list",list);
        return "study";
    }
}
