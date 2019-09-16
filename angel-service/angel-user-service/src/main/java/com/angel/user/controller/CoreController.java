package com.angel.user.controller;//自行导入所需依赖包

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CoreController {


    //测试页面，完成认证后即可访问
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        System.out.println("index");
        return "index";
    }


    //测试页面，即使完成认证，也仅允许权限中含“ADMIN”的用户访问

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority(ADMIN)")
    public String test() {
        System.out.println("test");
        return "xxx";
    }


    @RequestMapping(value = "/userinfo", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('BASE')")
    public String getUserInfo() {
        return "sss";
    }


    //自定义错误页面访问路径，无需对路径进行权限配置，框架可自行处理错误页面的访问权限

    @RequestMapping(value = "/err/{code}", method = RequestMethod.GET)
    public String ERR400(@PathVariable String code) {
        String path = "/errpage/" + code;
        System.out.println(path);
        return path + "";
    }

}