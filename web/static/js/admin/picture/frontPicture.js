$(function () {

    //渲染表格
    layui.table.render({
        elem : '#table',
        url : '/api/frontPicture/list',
        method: 'post',
        where: {
            token : getToken()
        },
        page: true,
        cols: [[
            {type:'numbers'},
            {field:'brandName', sort: true, title: '品牌'},
            {field:'modelName',sort:true,title: '型号'},
            {field:'img',sort:true,title:'图片',templet:function (d) {
                if (d.img === null) {
                    return '无';
                } else {
                    return '<a class="layui-btn layui-btn-primary layui-btn-xs" onclick="showPicModel(\''+d.img+'\')">查看图片</a>';
                }
            }},
            {align:'center', toolbar: '#barTpl', minWidth: 180, title: '操作'}
        ]]

    });

    //搜索按钮点击事件
    $("#searchBtn").click(function(){
        doSearch(table);
    });

    //工具条点击事件
    layui.table.on('tool(table)', function(obj){
        var data = obj.data;
        var layEvent = obj.event;

        if(layEvent === 'edit'){ //修改
            showEditModel(data);
        }
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
    layui.form.on('select(hasImgSelect)',function (data) {
        var value = data.value;
        layui.table.reload('table', {where: {hasImg: value,brandName:$('#brandName').val().trim(),modelName:$('#modelName').val().trim()}});
    });

});

function doSearch(table) {
    layui.table.reload('table', {where: {hasImg: $('#hasImgSelect').val(),modelName:$('#modelName').val().trim(),brandName:$('#brandName').val().trim()}});
}

//显示图片
function showPicModel(img) {
    var imagePath = img.substring(0,img.indexOf('.'));
    var type = img.substring(img.indexOf('.') + 1);
    layer.open({
        type:1,
        title:'查看图片',
        area:['350px','400px'],
        offset: '120px',
        content:'<div><img onerror="this.src=defaultPic()" style="width: 100%;height: 100%" src="/image/'+imagePath+'/'+type+'"/></div>'
    })
}

function defaultPic() {
    return "/static/images/no_pic.jpg";
}

//显示表单弹窗
function showEditModel(data) {
    layer.open({
        type: 1,
        title: '管理前台图片',
        area: '450px',
        offset: '120px',
        content: $("#addModel").html()
    });
    $("#editForm")[0].reset();
    if (data != null) {
        $('#editForm input[name=modelId]').val(data.modelId);
        $("#editForm input[name=modelName]").val(data.modelName);
        $('#editForm input[name=brandName]').val(data.brandName);
        if (data.img !== null) {
            $('#demo1').attr('src', '/image/' + data.img.substring(0, data.img.indexOf('.')) + '/' + data.img.substring(data.img.indexOf('.') + 1));
        }
    }
    $("#btnCancel").click(function () {
        layer.closeAll('page');
    });

    //渲染上传按钮
    layui.use('upload', function () {
        var upload = layui.upload;
        var uploadInst = upload.render({
            elem: '#imgUpload'//绑定元素
            , url: '/upload'
            , before: function (obj) {
                obj.preview(function (index, file, result) {
                    $('#demo1').attr('src', result);
                });
            }
            , done: function (res) {
                //如果上传失败
                if (res.code !== 200) {
                    layer.msg("服务器繁忙，请重试");
                    $('#demo1').removeAttr("src");
                    return;
                }
                //上传成功
                document.getElementById("img_url").value = res.url;
            }
            , error: function () {
                layer.msg("服务器繁忙，请重试");
                $('#demo1').removeAttr("src");
            }
        });
    });
}