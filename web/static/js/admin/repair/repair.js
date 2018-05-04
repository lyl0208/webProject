$(function() {
    //渲染表格
    layui.table.render({
        elem : '#table',
        url : '/api/repair/list',
        method: 'post',
        where: {
            token : getToken()
        },
        page: true,
        cols: [[
            {type:'numbers'},
            {field:'imei', sort: true, title: 'IMEI'},
            {field:'state',sort: true, title: '手机状态',templet:function (d) {return d.state===1?'待翻新':d===2?'翻新中':d===3?'已上架':d===4?'已下架':'未知'}},
            {field:'degree',sort: true, title: '新旧程度'},
            {field:'damagedPart',sort: true, title: '损坏部位'},
            {field:'maintenanceProject', sort: true, title: '维修项目'},
            {field:'amountComplained', sort:true, title: '投入金额'},
            {align:'center', toolbar: '#barTpl', minWidth: 180, title: '操作'}
        ]]
    });

    //添加按钮点击事件
    $("#addBtn").click(function(){
        showEditModel(null);
    });

    //表单提交事件
    layui.form.on('submit(btnSubmit)', function(data) {
        data.field.token = getToken();
        var url = $("#editForm").attr("action");
        layer.load(1);
        $.post(url, data.field, function(data){
            layer.closeAll('loading');
            if(data.code===200){
                layer.msg(data.msg,{icon: 1});
                layer.closeAll('page');
                layui.table.reload('table', {});
            }else{
                layer.msg(data.msg,{icon: 2});
            }
        }, "JSON");
        return false;
    });

    //搜索选择事件
    layui.form.on('select(stateSelect)',function (data) {
       var value = data.value;
       console.log("选择：" + value);
       layui.table.reload('table', {where: {state: value}});
    });

    //工具条点击事件
    layui.table.on('tool(table)', function(obj){
        var data = obj.data;
        var layEvent = obj.event;

        if(layEvent === 'fix'){ //翻新
            showEditModel(data);
        } else if(layEvent === 'finish'){ //上架
            doDelete(obj);
        }
    });

    //搜索按钮点击事件
    $("#searchBtn").click(function(){
        doSearch(table);
    });


});

//显示表单弹窗
function showEditModel(data){
    layer.open({
        type: 1,
        title: data==null?"添加手机档案":"修改手机档案",
        area: '450px',
        offset: '120px',
        content: $("#addModel").html()
    });
    $("#editForm")[0].reset();
    $("#editForm").attr("action","/api/phone/addPhone");
    if(data!=null){
        $('#editForm input[name=phoneId]').val(data.phoneId);
        $("#editForm input[name=IMEI]").val(data.imei);
        $("#editForm input[name=degree]").val(data.degree);
        $("#editForm input[name=damagedPart]").val(data.damagedPart);
        $("#editForm input[name=state]").val(data.state);
        $("#editForm input[name=recoveryPrice]").val(data.recoveryPrice);
        $('#editForm input[name=referenceSellingPrice]').val(data.referenceSellingPrice);
        $("#editForm").attr("action","/api/phone/editPhone");
        if (data.protection === 1) {
            $('#protectionYes').attr("checked","checked");
            $('#protectionNo').removeAttribute("checked");
        } else if (data.protection === 0) {
            $('#protectionNo').attr("checked","checked");
            $('#protectionYes').removeAttribute("checked");
        }
        $("#editForm").attr("action","/api/phone/editPhone");
    }
    $("#btnCancel").click(function(){
        layer.closeAll('page');
    });
    //表单验证
    layui.form.verify({
        money: function (value,item) {
            var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
            if (!reg.test(value)) {
                return "请输入正确的金钱格式";
            }
        },
        IMEI:[ /^[\S]{15,17}$/
            , '请输入正确的IMEI']
    });
}

//搜索
function doSearch(table){
    var key = $("#searchKey").val();
    var value = $("#searchValue").val();
    if (value=='') {
        key = '';
    }
    layui.table.reload('table', {where: {searchKey: key,searchValue: value}});
}

function initBrandSelect(brandId) {

    layer.load(1);
    $.post("/api/phone/getBrands",{token:getToken()},function (data) {
        layui.laytpl(brandSelect.innerHTML).render(data,function (html) {
            $('#brand-select').html(html);
            if (brandId !== null && brandId !== undefined){
                $('#brand-select').val(brandId);
                selectedBrand = brandId;
            }
            layui.form.render();
            layer.closeAll('loading');
        })
    });


}