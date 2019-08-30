package com.angel.common.gen.controller;

import com.angel.common.base.controller.BaseController;
import com.angel.common.base.http.PageData;
import com.angel.common.gen.service.ITableService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("table")
public class TableController extends BaseController {

    @Autowired
    ITableService iTableService;


    @RequestMapping("list")
    public String page() {
        return "view/code_gen/table_list";
    }

    @RequestMapping("listColumn")
    public ModelAndView listColumnPage(String tableName) {
        return mav("view/code_gen/column_list").addObject("tableName",tableName);
    }


    @RequestMapping("listTable")
    @ResponseBody
    public IPage<Map> listTable(PageData page) {
        return iTableService.listTable(page);
    }

    @RequestMapping("listTableColumn")
    @ResponseBody
    public IPage<Map> listTableColumn(PageData page,String tableName) {
        return iTableService.listTableColumn(page,tableName);
    }
}
