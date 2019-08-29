package com.angel.common.gen.controller;

import com.angel.common.base.http.R;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * @description:数据源管理
 * @author ailikes
 * @since 2019-9-29
 * @version 1.0.0
 */
@Controller
@RequestMapping("dataSource")
public class DataSourceController {

    @Autowired
    private DynamicDataSourceProvider ymlDynamicDataSourceProvider;

    /**
     * @description:获取当前数据源
     * @author ailikes
     * @since 2019-9-29
     * @version 1.0.0
     */
    @RequestMapping("get")
    @ResponseBody
    public List<String> getDataSoource(){
        List<String> list = Lists.newArrayList();
        // 获取配置的数据源
        Map<String, DataSource> dataSources = ymlDynamicDataSourceProvider.loadDataSources();
        for(Map.Entry<String, DataSource> entry : dataSources.entrySet()){
            String mapKey = entry.getKey();
            list.add(entry.getKey());
        }
        return list;
    }

    /**
     * @description:获取当前数据源
     * @author ailikes
     * @since 2019-9-29
     * @version 1.0.0
     */
    @RequestMapping("set")
    @ResponseBody
    public R getDataSoource(HttpServletRequest request,HttpServletResponse response, String dataSourceNname){
        request.getSession().setAttribute("tenantName",dataSourceNname);
        return R.ok();
    }

}
