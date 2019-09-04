package com.angel.common.gen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestOperations;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "view/index";
    }

        @Autowired
    private RestOperations restOperations;

    @GetMapping("/personInfo")
    public ModelAndView person() {
        ModelAndView mav = new ModelAndView("personinfo");
        String personResourceUrl = "http://localhost:9000/person";
        mav.addObject("person", restOperations.getForObject(personResourceUrl, String.class));
        return mav;
    }
}
