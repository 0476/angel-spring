package com.angel.common.gen.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @description:表结构持久化操作
 * @author ailikes
 * @since 2019-08-29
 * @version 1.0.0
 */
@DS("#session.tenantName")
public interface TableMapper {

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
