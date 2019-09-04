$(function(){
    layui.use(['element','table','form'], function(){
        TableColumnApp.init();
    });
})
var TableColumnApp={
    /**
     * 初始化
     */
    init:function(){

        this.initTable();
    }
    ,initTable:function(){
        layui.table.render({
            elem: '#columnList'
            ,height: 'full-40'
            ,url: '/table/listTableColumn' //数据接口
            ,where:{
                tableName:tableName
            }
            ,defaultToolbar: [ 'print', 'exports','filter']
            // ,toolbar: 'default'
            ,toolbar: false
            ,autoSort:false
            ,limit:10
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
                ,{field: 'columnName', title: '字段名', width:180}
                ,{field: 'columnKey', title: '约束', width:80}
                ,{field: 'isNullable', title: '可为空', width:80}
                ,{field: 'columnDefault', title: '默认值', width:100}
                ,{field: 'columnType', title: '字段类型', width: 120}
                ,{field: 'dataType', title: '数据类型', width: 100}
                ,{field: 'columnComment', title: '字段说明'}
            ]]
            ,done: function(res, curr, count) {

            }
        });
    }
}