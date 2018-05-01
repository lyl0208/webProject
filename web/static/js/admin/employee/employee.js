$(function() {
    //渲染表格
    layui.table.render({
        elem : '#table',
        url : '/api/user/listUser',
        method: 'post',
        where: {
            token : getToken()
        },
        page: true,
        cols: [[
            {type:'numbers'},
            {field:'userId', sort: true, title: 'ID'},
            {field:'username', sort: true, title: '账号'},
            {field:'realName', sort: true, title: '姓名'},
            {field:'createTime', sort: true, templet:function(d){ return layui.util.toDateString(d.createTime); }, title: '创建时间'},
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
    $("#editForm").attr("action","/api/user/addUser");
    if(data!=null){
        $("#editForm input[name=userId]").val(data.userId);
        $("#editForm input[name=username]").val(data.username);
        $("#editForm input[name=realName]").val(data.realName);
        $("#editForm").attr("action","/api/user/editUser");
    }
    $("#btnCancel").click(function(){
        layer.closeAll('page');
    });

}

//删除
function doDelete(obj){
    layer.confirm('确定要删除吗？', function(index){
        layer.close(index);
        layer.load(1);
        $.ajax({
            url: "/api/user/deleteUser",
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
        $.post("/api/user/resetPassword", {
            token: getToken(),
            userId:userId
        }, function(data){
            layer.closeAll('loading');
            if(data.code===200){
                layer.msg(data.msg,{icon: 1});
            }else{
                layer.msg(data.msg,{icon: 2});
            }
        },"JSON");
    });
}