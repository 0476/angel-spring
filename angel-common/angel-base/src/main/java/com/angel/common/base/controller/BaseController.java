package com.angel.common.base.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * @description:控制层基类
 * @author ailikes
 * @since 2019--8-30
 * @version 1.0.0
 */
public class BaseController  {

    public ModelAndView mav(){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
        return mav;
    }

    public ModelAndView mav(String viewName){
        ModelAndView mav = new ModelAndView(viewName);
        return mav;
    }

}
