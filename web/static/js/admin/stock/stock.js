$(function () {
    //渲染表格
    layui.table.render({
        elem: '#table',
        url: '/api/stock/list',
        method: 'post',
        where: {
            token: getToken()
        },
        page: true,
        cols: [[
            {type: 'numbers'},
            {field: 'brandName', sort: true, title: '品牌'},
            {field: 'modelName', sort: true, title: '型号'},
            {field: 'colorName', sort: true, title: '颜色'},
            {field: 'memoryName', sort: true, title: '内存'},
            {field: 'number', sort: true, title: '数量'}
        ]]
    });

    //搜索按钮点击事件
    $("#searchBtn").click(function () {
        doSearch(table);
    });

});


//搜索
function doSearch(table) {
    layui.table.reload('table', {
        where: {
            brandName: $('#brandName').val().trim(),
            modelName: $("#modelName").val().trim(),
            colorName: $("#colorName").val().trim(),
            memoryName: $('#memoryName').val().trim()
        }
    });
}
