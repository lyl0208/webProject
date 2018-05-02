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
            {field:'brandName', sort: true, title: '品牌'},
            {field:'modelName', sort: true, title: '型号'},
            {field:'colorName',sort:true,title:'颜色'},
            {field:'memoryName',sort:true,title:'内存'},
            {field:'degree',sort:true,title:'损坏程度'},
            {field:'protection',sort:true,title:'是否在保',templet:function (d) {return d.protection==='1'?'是':d.protection==='0'?'否':'未知'}},
            {field:'damagedPart',sort:true,title:'损坏部位'},
            {field:'state',sort:true,title:'状态',templet:function (d) {return d.state==='1'?'待翻新':d.state==='2'?'翻新中':d.state==='3'?'已上架':d.state==='4'?'已下架':'未知'}},
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
    if (data == null) {
        initBrandSelect();
    }
    else {
        initBrandSelect(data.brandId);
        initModelSelect(data.brandId, data.modelId);
        initColorSelect(data.modelId, data.colorId);
        initMemorySelect(data.modelId, data.memoryId);
    }
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

//初始化品牌下拉框
var selectedBrand;
var selectedModel;
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

    //监听品牌选择
    layui.form.on('select(brandSelect)',function (data) {
        var brandId = data.value;
        selectedBrand = data.value;
        initModelSelect(brandId,null);
        clearMemorySelect();
        clearColorSelect();
    });

    //监听型号选择
    layui.form.on('select(modelSelect)',function (data) {
        var modelId = data.value;
        selectedModel = data.value;
        initColorSelect(modelId,null);
        initMemorySelect(modelId,null);
    })

}


//清除型号下拉框
function clearModelSelect() {
    $('#model-select').html('');
}
//清除颜色下拉框
function clearColorSelect() {
    $('#color-select').html('');
}

//清除内存下拉框
function clearMemorySelect() {
    $('#memory-select').html('');
}




//初始化型号下拉框
function initModelSelect(brandId,modelId) {
    layer.load(1);
    $.post("/api/phone/getModels",{token:getToken(),brandId:brandId},function (data) {
        layui.laytpl(modelSelect.innerHTML).render(data,function(html){
            $('#model-select').html(html);
            if (modelId !== null && modelId !== undefined){
                $('#model-select').val(modelId);
                selectedModel = modelId;
            }
            layui.form.render();
            layer.closeAll('loading');
        })
    })
}

//初始化颜色下拉框
function initColorSelect(modelId,colorId) {
    layer.load(1);
    $.post("/api/phone/getColors",{token:getToken(),modelId:modelId},function (data) {
        layui.laytpl(colorSelect.innerHTML).render(data,function(html){
            $('#color-select').html(html);
            if (colorId !== null && colorId !== undefined){
                $('#color-select').val(colorId);
            }
            layui.form.render();
            layer.closeAll('loading');
        })
    })
}

//初始化内存下拉框
function initMemorySelect(modelId,memoryId) {
    layer.load(1);
    $.post("/api/phone/getMemorys",{token:getToken(),modelId:modelId},function (data) {
        layui.laytpl(memorySelect.innerHTML).render(data,function(html){
            $('#memory-select').html(html);
            if (memoryId !== null && memoryId !== undefined){
                $('#memory-select').val(memoryId);
            }
            layui.form.render();
            layer.closeAll('loading');
        })
    })
}


function addBrand() {
    layer.prompt({title:'添加品牌'},function (val,index) {
        if (val.length === 0) {
            layer.msg('品牌不得为空');
        }
        $.post('/api/phone/addBrand',{token:getToken(),brandName:val},function (data) {
            if (data.code === 200) {
                initBrandSelect(data.brand.brandId);
                clearModelSelect();
                clearColorSelect();
                clearMemorySelect();
            }
            layer.msg(data.msg);
            layer.close(index);
        });
    })
}

function addModel() {
    if (selectedBrand === null || selectedBrand === '') {
        layer.msg("请先选择品牌");
        return;
    }

    layer.prompt({title:'添加型号'},function (val,index) {
        if (val.length === 0) {
            layer.msg('型号不得为空');
        }
        $.post('/api/phone/addModel',{token:getToken(),modelName:val,brandId:selectedBrand},function (data) {
            if (data.code === 200) {
                initModelSelect(selectedBrand,data.model.modelId);
                clearColorSelect();
                clearMemorySelect();
            }
            layer.msg(data.msg);
            layer.close(index);
        });
    })
}

function addColor() {
    if (selectedModel === null || selectedModel === '') {
        layer.msg("请先选择型号");
        return;
    }

    layer.prompt({title:'添加颜色'},function (val,index) {
        if (val.length === 0) {
            layer.msg('颜色不得为空');
        }
        $.post('/api/phone/addColor',{token:getToken(),colorName:val,modelId:selectedModel},function (data) {
            if (data.code === 200) {
                initColorSelect(selectedModel,data.color.colorId);
            }
            layer.msg(data.msg);
            layer.close(index);
        });
    })
}

function addMemory() {
    if (selectedModel === null || selectedModel === '') {
        layer.msg("请先选择型号");
        return;
    }

    layer.prompt({title:'添加内存'},function (val,index) {
        if (val.length === 0) {
            layer.msg('内存不得为空');
        }
        $.post('/api/phone/addMemory',{token:getToken(),memoryName:val,modelId:selectedModel},function (data) {
            if (data.code === 200) {
                initMemorySelect(selectedModel,data.memory.memoryId);
            }
            layer.msg(data.msg);
            layer.close(index);
        });
    })
}