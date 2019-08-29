package com.angel.common.gen.controller;

import com.angel.common.gen.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("table")
public class TableController {

    @Autowired
    ITableService iTableService;

    @RequestMapping("listTable")
    @ResponseBody
    public List<Map> listTable() {
        return iTableService.listTable();
    }

    @RequestMapping("listTableColumn")
    @ResponseBody
    public List<Map> listTableColumn(String tableName) {
        return iTableService.listTableColumn(tableName);
    }
}
