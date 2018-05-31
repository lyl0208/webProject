$(function () {

    //渲染表格
    layui.table.render({
        elem : '#table',
        url : '/api/finance/list',
        method: 'post',
        where: {
            token:getToken()
        },
        page: true,
        cols: [[
            {type:'numbers'},
            {field:'brandName', sort: true, title: '品牌'},
            {field:'modelName',sort:true,title: '型号'},
            {field:'colorName',sort:true,title: '颜色'},
            {field:'memoryName',sort:true,title:'内存'},
            {field:'saleNumber',sort:true,title:'销量'},
            {field:'recoveryPrice',sort:true,title:'总回收价'},
            {field:'amountComplained',sort:true,title:'总维修价'},
            {field:'transactionPrice',sort:true,title:'总售价'},
            {field:'profit',sort:true,title:'总利润'},
            {align:'center', toolbar: '#barTpl', minWidth: 100, title: '操作'}
        ]],
        done:function () {
            $.post({
                url:'/api/finance/getTotalProfit',
                data:{token:getToken(),brandName: $('#brandName').val().trim(),modelName: $('#modelName').val().trim(),memoryName: $('#memoryName').val().trim(),colorName: $('#colorName').val().trim(),sellDateStart:$('#sellDateStart').val().trim(),sellDateEnd:$('#sellDateEnd').val().trim()},
                success:function (data) {
                    $('#totalProfit').html(data);
                },
                error:function () {
                    $('#totalProfit').html('加载失败，请重试');
                }
            })
        }
    });

    //搜索按钮点击事件
    $("#searchBtn").click(function(){
        doSearch(table);
    });


    //工具条点击事件
    layui.table.on('tool(table)', function(obj){
        var data = obj.data;
        var layEvent = obj.event;

        if(layEvent === 'checkDetail'){ //翻新
            showFinanceDetailModel(data);
        }
    });

    layui.use('laydate',function(){
       var laydate = layui.laydate;

       laydate.render({
          elem:'#sellDateStart'
       });

       laydate.render({
           elem:'#sellDateEnd'
       })

    });

});

function doSearch(table) {
    layui.table.reload('table', {where: {brandName: $('#brandName').val().trim(),modelName: $('#modelName').val().trim(),memoryName: $('#memoryName').val().trim(),colorName: $('#colorName').val().trim(),sellDateStart:$('#sellDateStart').val().trim(),sellDateEnd:$('#sellDateEnd').val().trim()}});
}

function showFinanceDetailModel(data) {
    layer.open({
        type: 1,
        title: data.brandName+data.modelName+data.colorName+data.memoryName +'销量情况',
        area: ['600px','400px'],
        offset: '30px',
        content: $("#detailModel").html()
    });
    //渲染表格
    layui.table.render({
        elem : '#detailTable',
        url : '/api/finance/listDetail',
        method: 'post',
        limit:5,
        where: {
            token : getToken(),
            brandName:data.brandName,
            modelName:data.modelName,
            colorName:data.colorName,
            memoryName:data.memoryName
        },
        page: true,
        cols: [[
            {type:'numbers'},
            {field:'imei',sort:true,title:'imei'},
            {field:'recoveryPrice',sort:true,title:'回收价'},
            {field:'amountComplained',sort:true,title:'维修价'},
            {field:'transactionPrice',sort:true,title:'售价'},
            {field:'profit',sort:true,title:'利润'}
        ]]
    });

    $('#detailSearchBtn').click(function () {
        doSearchDetail();
    })
}


function doSearchDetail() {
    layui.table.reload('detailTable',{where:{imei:$('#imei').val().trim()}});
}