$(function(){
    layui.use(['element','table','form'], function(){
        TableApp.init();
    });
})
var TableApp={
    /**
     * 初始化
     */
    init:function(){
        this.initForm();
    }
    /**
     * 初始化表单
     */
    ,initForm:function(){
        $.ajax({
            url:'/dataSource/get'
            ,success:function (data) {
                var optionHtml = '';
                $.each(data,function (index,item) {
                    optionHtml = optionHtml + '<option value="'+item+'">'+item+'</option>';
                });
                $("#dataSource").html(optionHtml);
                layui.form.render();
                TableApp.initDataSourceListener();
                TableApp.setDataSource($("#dataSource").val());
            }
        });
    }
    ,initDataSourceListener:function () {
        layui.form.on('select(dataSource)', function(data){
            TableApp.setDataSource(data.value);
        });
    }
    ,initTableListToolbarListener:function () {
        //监听事件
        layui.table.on('toolbar(tableListFilter)', function(obj){
            console.log(obj);
            var checkStatus = layui.table.checkStatus(obj.config.id); //idTest 即为基础参数 id 对应的值
            console.log(checkStatus.data) //获取选中行的数据
            switch(obj.event){
                case 'genCode':
                    if(checkStatus.data == null || checkStatus.data.length == 0){
                        layer.msg('请选择表');
                    }else{
                        TableApp.openGenWindow(checkStatus.data[0]['TABLE_NAME']);
                    }
                    break;
                case 'addTable':
                    layer.msg('添加');
                    break;
                case 'deleteTable':
                    layer.msg('删除');
                    break;
                case 'updateTable':
                    layer.msg('编辑');
                    break;
            };
        });
        //监听行事件
        layui.table.on('tool(tableListFilter)', function(obj){
            var data = obj.data; //获得当前行数据
            switch(obj.event){
                case 'queryDetail':
                    TableApp.openDetailWindow(data['TABLE_NAME']);
                    break;
            };
        });
    }
    ,openGenWindow:function (tableName) {
        layer.msg('代码生成面板：'+tableName);
    }
    ,openDetailWindow:function (tableName) {
        var index = layer.open({
             title:'表详情'
            ,type:2
            ,area:['1000px','680px']
            ,content: '/table/listColumn?tableName='+tableName
            ,btn:['关闭']
        });
    }
    ,setDataSource:function(dataSource){
        $.ajax({
            url:'/dataSource/set'
            ,data:{
                dataSourceNname:dataSource
            }
            ,success:function (data) {
                TableApp.initTable();
            }
        });
    }
    ,initTable:function(){
        layui.table.render({
            elem: '#tableList'
            ,height: 'full-140'
            ,url: '/table/listTable' //数据接口
            ,defaultToolbar: [ 'print', 'exports','filter']
            // ,toolbar: 'default'
            ,toolbar: '#tableListToolbar'
            ,autoSort:false
            ,limit:15
            ,request:{
                 pageName:"current"
                ,limitName: "size"
            }
            ,response:{
                dataName:"records",
                countName:'total'
            }
            ,page: {
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                ,limits:[10,20,50,100,200,500]
                ,groups: 5
                ,first: '首页'
                ,last: '末页'
            }
            ,cols: [[ //表头
                 {field: 'TABLE_NAME', title: '选择', width:40,type:'radio'}
                ,{field: 'TABLE_NAME', title: '表名', width:280,templet: '#titleToolbar'}
                ,{field: 'TABLE_SCHEMA', title: '数据库', width:180}
                ,{field: 'TABLE_TYPE', title: '表类型', width:140}
                ,{field: 'TABLE_COLLATION', title: '字符集', width: 140}
                ,{field: 'ENGINE', title: '表引擎', width: 80}
                ,{field: 'TABLE_COMMENT', title: '表说明'}
            ]]
            ,done: function(res, curr, count) {
                TableApp.initTableListToolbarListener();
            }
        });
    }
}