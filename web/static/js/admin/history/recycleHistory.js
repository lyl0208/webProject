$(function () {

    //渲染表格
    layui.table.render({
        elem : '#table',
        url : '/api/history/recycleHistory',
        method: 'post',
        where: {
            token : getToken()
        },
        page: true,
        cols: [[
            {type:'numbers'},
            {field:'serialNumber', sort: true, title: '回收流水号'},
            {field:'imei',sort:true,title: '手机imei'},
            {field:'recycleDate',sort:true,title: '回收日期',templet:function (d) {
                    return layui.util.toDateString(d.recycleDate,'yyyy-MM-dd HH:mm:ss')
                }},
            {field:'brandName',sort:true,title:'品牌'},
            {field:'modelName',sort:true,title:'型号'},
            {field:'colorName',sort:true,title:'颜色'},
            {field:'memoryName',sort:true,title:'内存'},
            {field:'recyclePrice',sort:true,title:'回收价'}
        ]]
    });

    //搜索按钮点击事件
    $("#searchBtn").click(function(){
        doSearch(table);
    });


});

function doSearch(table) {
    layui.table.reload('table', {where: {serialNumber: $('#serialNumber').val().trim()}});
}