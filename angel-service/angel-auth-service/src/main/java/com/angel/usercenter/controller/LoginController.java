package com.angel.usercenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * @Description:
 * @Author ailikes
 * @Since 2019-09-04 19:49
 * @Date 2019-09-04 19:49
 * @Version 1.0.0
 **/
@Controller
@RequestMapping
public class LoginController {

    @RequestMapping("login")
    public ModelAndView loginPage(){
        return new ModelAndView("login");
    }

    @RequestMapping("index")
    public ModelAndView index(){
        return new ModelAndView(new MappingJackson2JsonView()).addObject("index","index");
    }
}
