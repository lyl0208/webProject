$(function() {
    //渲染表格
    layui.table.render({
        elem : '#table',
        url : '/api/phone/listPhone',
        method: 'post',
        where: {
            token : getToken()
        },
        page: true,
        cols: [[
            {type:'numbers'},
            {field:'imei', sort: true, title: 'IMEI'},
            {field:'brand', sort: true, title: '品牌'},
            {field:'model', sort: true, title: '型号'},
            {field:'color',sort:true,title:'颜色'},
            {field:'memory',sort:true,title:'内存'},
            {field:'degree',sort:true,title:'损坏程度'},
            {field:'protection',sort:true,title:'是否在保',templet:function (d) {return d==='1'?'是':d==='0'?'否':'未知'}},
            {field:'damagedPart',sort:true,title:'损坏部位'},
            {field:'state',sort:true,title:'状态',templet:function (d) {return d==='1'?'待翻新':d==='2'?'翻新中':d==='3'?'已上架':d==='4'?'已下架':'未知'}},
            {field:'recoveryPrice',sort:true,title:'回收价'},
            {field:'referenceSellingPrice',sort:true,title:'参考销售价'},
            {field:'sellingId',sort:true,title:'销售明细编号'},
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

    //工具条点击事件
    layui.table.on('tool(table)', function(obj){
        var data = obj.data;
        var layEvent = obj.event;

        if(layEvent === 'edit'){ //修改
            showEditModel(data);
        } else if(layEvent === 'del'){ //删除
            doDelete(obj);
        } else if(layEvent === 'reset'){ //重置密码
            doReSet(obj.data.userId);
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
        title: data==null?"添加用户":"修改用户",
        area: '450px',
        offset: '120px',
        content: $("#addModel").html()
    });
    $("#editForm")[0].reset();
    $("#editForm").attr("action","/api/phone/addPhone");
    if(data!=null){
        $("#editForm input[name=IMEI]").val(data.imei);
        $("#editForm input[name=brand]").val(data.brand);
        $("#editForm input[name=model]").val(data.model);
        $("#editForm input[name=color]").val(data.color);
        $("#editForm input[name=memory]").val(data.memory);
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
    })

}

//删除
function doDelete(obj){
    layer.confirm('确定要删除吗？', function(index){
        layer.close(index);
        layer.load(1);
        $.ajax({
            url: "api/user/deleteUser",
            type: "post",
            data:{userId:obj.data.userId,token:getToken()},
            dataType: "JSON",
            success: function(data){
                layer.closeAll('loading');
                if(data.code===200){
                    layer.msg(data.msg,{icon: 1});
                    obj.del();
                }else{
                    layer.msg(data.msg,{icon: 2});
                }
            }
        });
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

//重置密码
function doReSet(userId){
    layer.confirm('确定要重置密码吗？', function(index){
        layer.close(index);
        layer.load(1);
        $.post("api/user/psw/"+userId, {
            token: getToken(),
            _method: "PUT"
        }, function(data){
            layer.closeAll('loading');
            if(data.code==200){
                layer.msg(data.msg,{icon: 1});
            }else{
                layer.msg(data.msg,{icon: 2});
            }
        },"JSON");
    });
}