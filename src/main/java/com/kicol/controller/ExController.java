package com.kicol.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 韩老魔
 * @Date: 2019/4/9 0009 18:44
 */
@Controller
public class ExController {
    @RequestMapping("/index")
    public String index(){
        System.out.println("index");
        int i=1/0;
        return "index";
    }
}
