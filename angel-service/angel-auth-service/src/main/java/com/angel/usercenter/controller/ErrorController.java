package com.angel.usercenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author ailikes
 * @Since 2019-09-05 10:54
 * @Date 2019-09-05 10:54
 * @Version 1.0.0
 **/
@Controller
@RequestMapping("error")
public class ErrorController {

    @RequestMapping("401")
    public String err401(){
        return "error/error_401";
    }
}
