package com.angel.common.gen.service.impl;

import com.angel.common.base.http.PageData;
import com.angel.common.gen.mapper.TableMapper;
import com.angel.common.gen.service.ITableService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TableServiceImpl implements ITableService {

    @Autowired
    private TableMapper tableMapper;

    @Override
    public IPage<Map> listTable(PageData page) {
        return tableMapper.listTable(page);
    }

    @Override
    public IPage<Map> listTableColumn(PageData page,String tableName) {
        return tableMapper.listTableColumn(page,tableName);
    }
}
