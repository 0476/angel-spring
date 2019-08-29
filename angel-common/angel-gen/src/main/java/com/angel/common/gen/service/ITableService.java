package com.angel.common.gen.service;

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
    List<Map> listTable();

    /**
     * @description:查询表中的字段
     * @param tableName
     * @return
     */
    List<Map> listTableColumn(@Param("tableName") String tableName);

}
