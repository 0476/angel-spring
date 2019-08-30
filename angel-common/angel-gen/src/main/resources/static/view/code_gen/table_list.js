$(function(){
    TableApp.init();
})
var TableApp={
    init:function(){
        this.initForm();
    }
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
                TableApp.initDataSourcsListener();
                TableApp.setDataSoource($("#dataSource").val());
            }
        });
    }
    ,initDataSourcsListener:function () {
        layui.form.on('select(dataSource)', function(data){
            TableApp.setDataSoource(data.value);
        });
    }
    ,setDataSoource:function(dataSource){
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
                 {field: 'TABLE_NAME', title: '选择', type:'radio'}
                ,{field: 'TABLE_NAME', title: '表名', width:280}
                ,{field: 'TABLE_CATALOG', title: '表目录', width:80}
                ,{field: 'TABLE_SCHEMA', title: '数据库', width:180}
                ,{field: 'TABLE_TYPE', title: '表类型', width:140}
                ,{field: 'TABLE_COLLATION', title: '字符集', width: 140}
                ,{field: 'ENGINE', title: '表引擎', width: 80}
                ,{field: 'TABLE_COMMENT', title: '表说明'}
            ]]
        });
    }
}