package com.angel.common.gen.service;

import com.angel.common.base.http.PageData;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @description:表结构服务类
 */
public interface ITableService {

    /**
     * @description:查询表结构
     * @return
     */
    IPage<Map> listTable(PageData page);
    /**
     * @description:查询表中的字段
     * @param tableName
     * @return
     */
    IPage<Map> listTableColumn(PageData page,@Param("tableName") String tableName);

}
