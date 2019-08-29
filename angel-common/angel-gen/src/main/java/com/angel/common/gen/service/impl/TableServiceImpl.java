package com.angel.common.gen.service.impl;

import com.angel.common.gen.mapper.TableMapper;
import com.angel.common.gen.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TableServiceImpl implements ITableService {

    @Autowired
    private TableMapper tableMapper;

    @Override
    public List<Map> listTable() {
        return tableMapper.listTable();
    }

    @Override
    public List<Map> listTableColumn(String tableName) {
        return tableMapper.listTableColumn(tableName);
    }
}
