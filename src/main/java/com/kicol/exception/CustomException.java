package com.kicol.exception;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 韩老魔
 * 全局捕获异常：异常通知类AOP
 * @Date: 2019/4/7 0007 15:11
 */
@ControllerAdvice
public class CustomException {
    //拦截运行异常
    @ExceptionHandler(RuntimeException.class)
    public String myException(Exception e) {
        /*
        * RuntimeException: 包含多个异常
        * */
        System.out.println("全局异常捕获进来了-----------");
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("code","400");
        map.put("message","运行异常");
        map.put("detail",e.getMessage());
        return "error";
    }

}
