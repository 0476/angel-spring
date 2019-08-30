var TableApp={
    initTable:function(){
        layui.table.render({
            elem: '#tableList'
            ,height: 'full-120'
            ,url: '/table/listTable' //数据接口
            ,autoSort:false
            ,request:{
                 pageName:"page"
                ,limitName: "size"
            }
            ,response:{
                dataName:"records"
            }
            ,page: {
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                ,groups: 1 //只显示 1 个连续页码
                ,first: false //不显示首页
                ,last: false //不显示尾页
            }
            ,cols: [[ //表头
                 {field: 'TABLE_CATALOG', title: '表目录', width:80}
                ,{field: 'TABLE_SCHEMA', title: '数据库', width:180}
                ,{field: 'TABLE_TYPE', title: '表类型', width:140}
                ,{field: 'TABLE_COLLATION', title: '字符集', width: 100}
                ,{field: 'ENGINE', title: '表引擎', width: 80},
                ,{field: 'TABLE_NAME', title: '表名', width:280}
                ,{field: 'TABLE_COMMENT', title: '表说明'}
            ]]
        });
    }
}