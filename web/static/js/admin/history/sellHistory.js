$(function () {

    //渲染表格
    layui.table.render({
        elem : '#table',
        url : '/api/history/sellHistory',
        method: 'post',
        where: {
            token : getToken()
        },
        page: true,
        cols: [[
            {type:'numbers'},
            {field:'sellingId', sort: true, title: '销售明细编号'},
            {field:'imei',sort:true,title: '手机imei'},
            {field:'operatorName',sort:true,title: '操作员'},
            {field:'saleDate',sort:true,title:'销售日期',templet:function (d) {
                    return layui.util.toDateString(d.saleDate,'yyyy-MM-dd HH:mm:ss');
                }},
            {field:'brandName',sort:true,title:'品牌'},
            {field:'modelName',sort:true,title:'型号'},
            {field:'colorName',sort:true,title:'颜色'},
            {field:'memoryName',sort:true,title:'内存'},
            {field:'transactionPrice',sort:true,title:'成交价'}
        ]]
    });


});